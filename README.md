# Project #: Hash tables

* Author: Austin Bartram
* Class: CS321 001
* Semester: Spring 2026

## Overview

This program implements a hash table using an open address that compares the performance of linear probing and double hashing. This program runs experiments using different levels of data-sources, load factors, and debugging. This runs these tests 
so that it can report the average and debugging and duplicates. 

## Reflection

The longest part of this project that I ran into the most troubles with is the HashtableExperiment. This section caused me the most amount of problems getting the files to fully parse. And for a while I did not have all the necessary files needed to run the program which led to a grand halt. Most of the whole experiment section led to the largest amount of time as that is what prolonged the amount of time I took on this project. I would like to see some more upgrades and some more in-depth programming to see the height of how far this project can go. 

What I did well with was getting the linear probing and the double hasing to work. Getting the math from m and n to compute together in the main experiment file was smooth and gave little to minor errors. The hashtable is where I ran nto some more issues with the parse and getting the function to work. 

## Compiling and Using

When the user enters the program they will be prompted to navigate the program. Once they go through that they will recieve a menu:
Usage: java HashtableExperiment <dataSource> <loadFactor> [debugLevel]
<dataSource>: 1=random, 2=dates, 3=word-list
<loadFactor>: between 0 and 1
<debugLevel>: 0=summary, 1=dump, 2=inserts

 then they should compile the program through:
    javac *.java
which will compile all the required java sources. 
Once thats done they should follow the format command of:
    java HashtableExperiment <dataSource> <loadFactor> [debugLevel]
and once they run that they will be left with the ran program that goes through linear probing and double hashing and a comparison of the two. 

And example of the program prompt is:
    java HashtableExperiment 3 0.5 1

## Results

When running the HashtableExperiment, and on a 0.5 rate for all 3 sections we get. 
######
For an Assignment of random numbers: 

HashtableExperiment: Found a twin prime table capacity: 95791                                                                                           
HashtableExperiment: Input: Random Numbers  Loadfactor: 0.50

        Using Linear Probing
HashtableExperiment: size of hash table is 47896
        Inserted 47896 elements, of which 0 were duplicates
        Avg. no. of probes = 1.50

        Using Double Hashing
HashtableExperiment: size of hash table is 47896
        Inserted 47896 elements, of which 0 were duplicates
        Avg. no. of probes = 1.39
######
For an assignment of dates: 

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Dates   Loadfactor: 0.50

        Using Linear Probing
HashtableExperiment: size of hash table is 47896
        Inserted 47896 elements, of which 0 were duplicates
        Avg. no. of probes = 1.13

        Using Double Hashing
HashtableExperiment: size of hash table is 47896
        Inserted 47896 elements, of which 0 were duplicates
        Avg. no. of probes = 1.29
######
For an assignment of inputs: 

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-List   Loadfactor: 0.50

        Using Linear Probing
HashtableExperiment: size of hash table is 47896
        Inserted 1305930 elements, of which 1258034 were duplicates
        Avg. no. of probes = 1.60

        Using Double Hashing
HashtableExperiment: size of hash table is 47896
        Inserted 1305930 elements, of which 1258034 were duplicates
        Avg. no. of probes = 1.39


## Sources used (this is VERY important!)

If you used any sources outside of the lecture notes, class lab files, or text book you need
to list them here.

In particular, explain what AI tool you used and how.  For example, if you used CoPilot to help
you write a function, you need to say that here and include the exact prompt you used. For each
project, you should only use the AI tools as instructed in the project.

## Notes

I would like to use my late tickets for this assignment.
