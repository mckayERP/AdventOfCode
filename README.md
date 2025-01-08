# Introduction

This is a repository of my Advent of Code solutions starting with 2024.  My goals with 
the solutions are to use java and test-driven design to generate the solution code.  Most of 
the solutions work reasonably fast, within seconds, but I'm not trying to optimize 
the solutions for speed beyond trying to run the solutions in a reasonable amount of time.

I'm also trying to stick with Clean Code principles as much as is reasonable.  If the tests
work, and I've solved the problem, refactoring isn't a priority.

The repository includes the sample problem inputs presented in the problem text but the 
actual problem inputs are not included. Some of the tests may not run without these 
inputs and the Main classes will not function at all.

I also started a module with utility classes that seemed to be useful across several of
the solutions.  These mostly deal with handling the input text files and classes related 
to 2-dimensional maps of objects.

## 2024 Notes

I managed to solve all the problems with a programmatic solution except day 24. I did not 
find a solution for day 24 part 2 and had to solve it by hand, using my code to describe where the 
problems were. This was simple for me visually but tough to do using logic. I may come back to 
this to find the correct algorithm.

I also almost gave up on the Day 21 - Keypad Conundrum. It took me a long time to figure out 
a solution that didn't cause a stack overflow.

Otherwise, the solutions sort of fell out of the test-driven approach.

The Chronospatial Computer problem on day 17 was one of my favorites.

One aspect of the solutions that was new to me was the use of Sets in java. These were very 
helpful in several of the problems but especially on Day 23 - Lan party.  Here the solution 
was to find the largest set of computers where the intersection of each of their connections
was the same large set.  Using the Set class made this rather straight forward.



