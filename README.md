Streams in Java 8 are a powerful and flexible tool for handling collections of data. They allow for functional-style operations on collections of objects and make it easier to perform complex data processing tasks such as filtering, mapping, and reducing.
**Overview**

A Stream in Java 8 represents a sequence of elements and supports different kinds of operations to perform computations on these elements. These operations can be either intermediate or terminal.

**Key Characteristics of Streams**

1.	Sequence of Elements: A stream provides a sequence of elements from a source (like collections, arrays, or I/O channels).
2.	Pipelining: Most stream operations return a stream themselves, which allows operations to be chained together to form a pipeline.
3.	Internal Iteration: Streams use internal iteration to traverse elements, unlike collections which use external iteration.

**Stream Operations**

**1. Intermediate Operations**
   
Intermediate operations return a new stream. They are lazy, meaning that they do not process the elements until a terminal operation is invoked. This helps in optimizing the operations.

•	**filter**: Filters elements based on a predicate. 

•	**map**: Transforms each element to another object. 

•	**flatMap**: Transforms each element into a stream of objects, and then concatenates all the streams. 

•	**distinct**: Returns a stream with duplicate elements removed. 

•	**sorted**: Sorts the elements of the stream. 

•	**peek**: Allows performing a specified operation on each element as they are encountered. 

•	**limit**: Truncates the stream to a specified length. 

•	**skip**: Skips the first n elements. 

**2. Terminal Operations**
   
Terminal operations produce a result or a side effect and mark the end of the stream. After the terminal operation is invoked, the stream pipeline is considered consumed and can no longer be used.

•	**forEach**: Performs an action for each element.

•	**toArray**: Returns an array containing the elements of the stream.

•	**reduce**: Reduces the elements of the stream to a single value.

•	**collect**: Performs a mutable reduction operation on the elements of the stream.

•	**min**: Finds the minimum element according to a comparator.

•	**max**: Finds the maximum element according to a comparator.

•	**count**: Returns the count of elements in the stream.

•	**anyMatch**: Returns true if any element matches the provided predicate.

•	**allMatch**: Returns true if all elements match the provided predicate.

•	**noneMatch**: Returns true if no elements match the provided predicate.

•	**findFirst**: Returns the first element of the stream.

•	**findAny**: Returns any element of the stream.


