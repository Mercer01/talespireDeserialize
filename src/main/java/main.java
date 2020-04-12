import com.google.gson.JsonObject;

import java.io.IOException;

public class main {

    static String othertest = "H4sIAAAAAAAACzv369xFRgZGho2TjHcYd5x0Xlmvdfh+6rw0RgYQaDjw/3+9HQPDiYNAtj0Q24FojgkBi3DJAQC5F3ODUAAAAA==";
    static String rotate = "H4sIAAAAAAAACzv369xFRgZGho2TjHcYd5x0Xlmvdfh+6rw0RgYQaDjw/3+9HQPDiYNAtj0Q24FojgkBi3DJAQC5F3ODUAAAAA==";
    static String fourxfour = "H4sIAAAAAAAACzv369xFRgZGho2TjHcYd5x0Xlmvdfh+6rw0NgYwOMjA0GDHwPADRNtD2GAaiA8cwCo3MWARQh/LIVR9xJqJrg+PfWDwACrHANTH4ABlOwAA45ADvdwAAAA=";
    static String complex = "H4sIAAAAAAAACzv369xFRgYmho2TjHcYd5x0Xlmvdfh+6rw0DoZDN3gvClpqixj7z38VayVw1LyRGSjGwHDiIANDgx0DwxEnIG0PYTfYczCAwA7schae13DqY8KjD2wmTN8BLPpWQOX2oMjxMCDLHUCTA7F3YNXH4bL2Hkjf///1GG5h+f//P0gfRA7VTBaoOyFye7DIIetzcIDIOTgAAMlERx58AQAA";
    static String willbreak = "H4sIAAAAAAAACzv369xFRgZWho2TjHcYd5x0Xlmvdfh+6rw0VgYGBlOlrHP+aS+cl7tcLTFTLpBlA4rd6Qx9VRXA67ffY8P0E4ZdFlxAMYFFhxl/T5nv0uM+0+6WXaEaSC/vRUFLbRFj//mvYq0Ejpo3MjGAgIAjA0ODHQPDh4NA2h7CBtFg4IBNjges8QFWOYi2BTjkQPgAVK4Bi74EqNwToBwDVI7BnuX///8IuTeocmC9MLkzqHIc9y8i5PagyjEh27cFzUxkuTVY5CZgdScDitwVVDkmZLkzePTtQZMD+W8CVrcwuK29h5Cbg8e+KahyKOE5B0943kGVA9uXgNV/kPCExe0DlLiFhBlM7gCWeIfJbUCVQzFzArb06YhdDjmdLcBiJkzfAixmboDq23EQEX4MjgCGk7BDlAMAAA==";
    static String test = "H4sIAAAAAAAACzv369xFRgYWhvs5R5XvJBT7LuQLWOJq8PoNI0PbYu1UR3dX5SWe+65a1e96KV3HBRT71RQW6OR8w7Xx7mbZX6pPJ4DUyTMZpUt+feW0nJHvkfuGmjyQGAOD26Et29c6MDBoHGRgYLBnYFCwB9E8DA32ILkU08dAOQmI3Pw6qBzHIQYGJ6icBpocCID0vcYpt2kWryOmHMg+LwJmguQssMg5HbryeCcWOZA7vXDIQeyDyKH7DyTnhEMO4hYGhhYsYQaTs8CUM1FqAMktajkIlTtg78JZ7ACiAUoWpfTcAQAA";

    public static void main(String[] args) throws IOException  {

        JsonObject output = DecodeToJson.decode(test);

        System.out.println(output);

    }


}
