# SideInput/Grouping problem

This is an example of values not matching when using an AvroCoder.

## Scenario
We have N values of `(timestamp, key, value)`. We want to
compute standard deviation for each key. We use sample standard deviation
formula (https://en.wikipedia.org/wiki/Standard_deviation):

![sampled standard deviation](stddev.svg)

What we expect to get is a list of values: `(key, std dev)` for a given time 
window (or global). 

## Implementation.

1. Compute mean and count per key.
2. Compute (x-mean)^2 per value.
3. Sum values per key.
4. Divide sum by count and compute square root of it.

The implementation could be found in [StdDevTransform](./StdDevTransform.java)

## Problem
When using a custom class with AvroCoder as a key the side input values
are not grouped properly. There is no side input value available.