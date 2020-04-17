# Talesprie Slab to JSON thingie.

#### Decode
```C#
uint MAGICHEX = 0xD1CEFACE
ushort CurrentVersion = 1;
ushort LayoutCount;

You then have LayoutCount instance of the Layout type

public struct Layout
{
 public readonly NGuid AssetId;     // 16byte v4 uuid. Each AssetId appears only once in each paste
 public readonly ushort AssetCount;
}

and then the asset data. 

public struct AssetCopyData
{
 public readonly UnityEngine.Bounds SelectionBounds;
 public readonly byte Rotation; // Value range is 0-15. Indicates 16 possible rotation positions. Only multiples of 4 are valid for tiles.
}
 ```
 They are stored in the same order as the layouts. The total number is the sum of all the AssetCount fields from Layout (but that's not super important).
 You can just loop once for each layout readonly out AssetCount elements from the asset data section and you'll have the right stuff.


Based on this specifiction by the talespire devs (thnx Baggers) this program will take an input string and convert it to a JSON object.

This was made with quite a bit of help by lagicarus/Oceanlord on the spec as well as there were some gaps

example:
```
H4sIAAAAAAAACzv369xFRgYmht71eR8Z67I99uXKL0oMm3OFhYGBoUWRL6kzRdClbaeS1eGIY94gMQaGAw4MDAvsGRianBgYGuwh7AZ7jv///wNprHIQfQKOEH4Fqj4w+wFUXw2KHIPz2nsQOQcgLoDKNSDpA7kFROegyYFAA1SuBlWOCVmuBIu+C0A5BgeIOxOg7AYHAKB2UtcoAQAA```
```
will be transformed into 

```Json
{
    "magichex": "CE FA CE D1 ",
    "Version": 1,
    "Layout": 2,
    "Assets": [{
        "UUID": "8daf6ef1-017e-6b48-be6d-1fa261569cd4",
        "Count": 4,
        "Layout": [{
            "location": {
                "x": 6.0,
                "y": 1.25,
                "z": 65.0
            },
            "size": {
                "x": 1.0,
                "y": 1.25,
                "z": 1.0
            },
            "Rotation": 8
        }, {
            "location": {
                "x": 4.0,
                "y": 1.25,
                "z": 65.0
            },
            "size": {
                "x": 1.0,
                "y": 1.25,
                "z": 1.0
            },
            "Rotation": 4
        }, {
            "location": {
                "x": 9.0,
                "y": 1.25,
                "z": 62.0
            },
            "size": {
                "x": 1.0,
                "y": 1.25,
                "z": 1.0
            },
            "Rotation": 8
        }, {
            "location": {
                "x": 7.0,
                "y": 1.25,
                "z": 63.0
            },
            "size": {
                "x": 1.0,
                "y": 1.25,
                "z": 1.0
            },
            "Rotation": 0
        }]
    }, {
        "UUID": "84210e62-8964-1144-86b9-223ac358c64b",
        "Count": 4,
        "Layout": [{
            "location": {
                "x": 7.0,
                "y": 3.0,
                "z": 60.0
            },
            "size": {
                "x": 1.0,
                "y": 1.0,
                "z": 1.0
            },
            "Rotation": 8
        }, {
            "location": {
                "x": 6.0,
                "y": 1.0,
                "z": 59.0
            },
            "size": {
                "x": 1.0,
                "y": 1.0,
                "z": 1.0
            },
            "Rotation": 8
        }, {
            "location": {
                "x": 4.0,
                "y": 1.0,
                "z": 63.0
            },
            "size": {
                "x": 1.0,
                "y": 1.0,
                "z": 1.0
            },
            "Rotation": 8
        }, {
            "location": {
                "x": 4.0,
                "y": 1.0,
                "z": 61.0
            },
            "size": {
                "x": 1.0,
                "y": 1.0,
                "z": 1.0
            },
            "Rotation": 8
        }]
    }],
    "Bounds": {
        "location": {
            "x": 6.5,
            "y": 2.0,
            "z": 62.0
        },
        "size": {
            "x": 3.5,
            "y": 2.0,
            "z": 4.0
        },
        "Rotation": 0
    }
}
```