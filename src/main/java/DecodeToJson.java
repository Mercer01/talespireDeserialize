import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

public class DecodeToJson {


    public static JsonObject decodedJson = new JsonObject();


    public static JsonObject decode(String input) throws IOException {


        byte[] decodeBytes = input.getBytes("UTF-8");
        byte[] decompressed = decompress(Base64.getDecoder().decode(decodeBytes));

        InputStream stream = new ByteArrayInputStream(decompressed);


        byte[] magicHex = new byte[4];
        readBytes(stream, magicHex);
        ByteBuffer bufferMagicHex = ByteBuffer.wrap(magicHex);
        decodedJson.addProperty("magichex", toHex(bufferMagicHex));


        //Read Version property
        byte[] Version = new byte[2];
        readBytes(stream, Version);
        decodedJson.addProperty("Version", getShortFromBytes(Version));

        //Read Layout Property
        byte[] LayoutsCount = new byte[2];
        readBytes(stream, LayoutsCount);
        int layoutCount = getShortFromBytes(LayoutsCount);
        decodedJson.addProperty("Layout", layoutCount);


        JsonArray Layouts = new JsonArray();

        for (int i = 0; i < layoutCount; i++) {

            JsonObject layout = new JsonObject();

            // Get UUID
            byte[] assetID = new byte[16];
            readBytes(stream, assetID);
            layout.addProperty("UUID", getUUIDFromBytes(assetID).toString());

            // Get the count for that specific asset
            byte[] assetCount = new byte[2];
            readBytes(stream, assetCount);
            layout.addProperty("Count", getShortFromBytes(assetCount));

            // Move forwards two bytes unsure why, but it works :shrug:?
            byte[] random = new byte[2];
            readBytes(stream, random);

            Layouts.add(layout);
        }

        //Get the amount of Different Tiles in the slab
        int size = Layouts.size();

        //Create object to store all of the assets in to add at the end
        JsonArray AllAssets = new JsonArray();
        for (int layoutIndex = 0; layoutIndex < size; layoutIndex++) {

            //Get the current record that were going to process
            JsonObject currentTile = Layouts.get(layoutIndex).getAsJsonObject();
            int AssetCount = currentTile.get("Count").getAsInt();


            JsonArray assets = new JsonArray();

            for (int i = 0; i < AssetCount; i++) {
                JsonObject asset = new JsonObject();
                JsonArray vectors = new JsonArray();

                for (int j = 0; j < 6; j++) {
                    byte[] vectorFloats = new byte[4];
                    readBytes(stream, vectorFloats);

                    ByteBuffer BuffVectorStream = ByteBuffer.wrap(vectorFloats);
                    BuffVectorStream.order(ByteOrder.LITTLE_ENDIAN);
                    float vectorFloat = BuffVectorStream.getFloat();
                    vectors.add(vectorFloat);
                }
                //This is currently two Vector3's I should deal with this better
                asset.add("VectorPair" + i, vectors);


                // get Rotation Header.
                byte[] Rotation = new byte[1];
                readBytes(stream, Rotation);

                ByteBuffer RotationBuffer = ByteBuffer.wrap(Rotation);
                RotationBuffer.order(ByteOrder.LITTLE_ENDIAN);
                asset.addProperty("Rotation", RotationBuffer.get());

                // Move 3 bytes forward to complete block. (Unsure why this exists)
                byte[] random2 = new byte[3];
                readBytes(stream, random2);

                assets.add(asset);
            }
            currentTile.add("Layout", assets);
            AllAssets.add(currentTile);
        }
        decodedJson.add("Assets", AllAssets);

        System.out.println("Bytes remaining " + stream.available());

        return decodedJson;

    }

    private static void readBytes(InputStream stream, byte[] buffer) {
        int length;
        try {
            length = stream.read(buffer,0,buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] decompress(final byte[] input) throws IOException
    {
        try (ByteArrayInputStream bin = new ByteArrayInputStream(input);
             GZIPInputStream gzipper = new GZIPInputStream(bin))
        {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len;
            while ((len = gzipper.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            gzipper.close();
            out.close();
            return out.toByteArray();
        }
    }

    public static short getShortFromBytes(byte[] bytes) {

        ByteBuffer buffAssetCount = ByteBuffer.wrap(bytes);
        buffAssetCount.order(ByteOrder.LITTLE_ENDIAN);

        return buffAssetCount.getShort();

    }

    public static UUID getUUIDFromBytes(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();

        return new UUID(high, low);
    }


    public static String toHex(ByteBuffer bb) {
        bb.order(ByteOrder.LITTLE_ENDIAN);
        StringBuilder sb = new StringBuilder();
        while (bb.hasRemaining()) {
            sb.append(String.format("%02X ", bb.get()));
        }
        return sb.toString();
    }
}
