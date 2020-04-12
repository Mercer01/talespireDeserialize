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
H4sIAAAAAAAACzv369xFRgYWhvs5R5XvJBT7LuQLWOJq8PoNI0PbYu1UR3dX5SWe+65a1e96KV3HBRT71RQW6OR8w7Xx7mbZX6pPJ4DUyTMZpUt+feW0nJHvkfuGmjyQGAOD26Et29c6MDBoHGRgYLBnYFCwB9E8DA32ILkU08dAOQmI3Pw6qBzHIQYGJ6icBpocCID0vcYpt2kWryOmHMg+LwJmguQssMg5HbryeCcWOZA7vXDIQeyDyKH7DyTnhEMO4hYGhhYsYQaTs8CUM1FqAMktajkIlTtg78JZ7ACiAUoWpfTcAQAA
```
will be transformed into 

```Json
{
	"magichex": "CE FA CE D1 ",
	"Version": 1,
	"Layout": 4,
	"Assets": [{
		"UUID": "df6cc523-dc60-734d-a10e-50a44530ebec",
		"Count": 1,
		"Layout": [{
			"VectorPair0": [-49.5, 5.4286747, -10.5, 0.5, 0.625, 0.5],
			"Rotation": 12
		}]
	}, {
		"UUID": "2b654147-4523-a449-bed5-3a7fbae91b7e",
		"Count": 10,
		"Layout": [{
			"VectorPair0": [-49.5, 7.1002674, -9.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair1": [-48.5, 7.1002674, -10.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair2": [-49.5, 7.3502674, -10.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair3": [-49.5, 8.850267, -10.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair4": [-50.5, 7.1002674, -10.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair5": [-49.5, 7.1002674, -11.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair6": [-48.5, 5.809061, -11.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair7": [-50.5, 5.809061, -11.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair8": [-50.5, 5.809061, -9.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}, {
			"VectorPair9": [-48.5, 5.809061, -9.5, 0.5, 0.99461365, 0.5],
			"Rotation": 12
		}]
	}, {
		"UUID": "fa825651-4243-d845-81dd-b31dfa25e590",
		"Count": 1,
		"Layout": [{
			"VectorPair0": [-49.5, 4.125, -10.5, 0.5, 0.625, 0.5],
			"Rotation": 12
		}]
	}, {
		"UUID": "1f023267-19f5-ea42-a701-0ee247b07c6e",
		"Count": 1,
		"Layout": [{
			"VectorPair0": [-49.5, 2.875, -10.5, 0.5, 0.625, 0.5],
			"Rotation": 12
		}]
	}]
}
```