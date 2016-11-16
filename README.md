# ProjectDistributor
Brut force algorithm

Each group will give a mark for each project.
The algorithm will take all the combination 
(project of group1, project of group2, ..., project of group n)
with project of group1 = a, project of group2 = b, ...
For each combination, it will calculate the injustice. 
Injustice = square(Mark of the group1 on the project a) + square(Mark of the group2 on the project b) + ...
During its travel, the algo will record the combination with the smaller injustice.

After that, the algorithm will check the the number of solution with the smallest injustice.
if the number of solution = 1, it's right and the unique combination is selected
else a random fonction will choose which combination is selected (but the injustice will not change !) 
The seed of the random function is function of the input data. To have the same result if you run again this algorithm with the same data.

