// Time Complexity : O(V+E) where V is number of vertices and E is number of edges
// Space Complexity : O(V+E) where V is the number of vertices and E is the number of edges (for the dependency map it is O(V+E) and inDegree array it only takes O(V) space and the queue takes O(V+E) space as we don't process all the edges at each vertex)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/**
 * Here, we are using BFS approach to traverse the graph level by level.
 * The first for loop populates the inDegree array and each index represents the number of prerequisites for that course. The dependency map is a mapping of each course to the list of courses that depend on it.
 * The second for loop adds all the independent courses (inDegree 0) to the queue and increments the count of independent courses.
 * if(count==numCourses) return true; if the count of independent courses is equal to the total number of courses, we can finish all the courses without any prerequisites hence it is true. when there are no independent courses and the queue is empty, it means there is a cycle in the graph and we cannot finish any course without finishing some other course hence it is false.
 * The while loop processes each independent course, and for each dependent course of that independent course, it decrements the inDegree by 1. If the inDegree of any dependent course becomes 0, it means all its prerequisites are met and it can be added to the queue and the count is incremented.
 * Finally, if the count of independent courses is equal to the total number of courses, we can finish all the courses hence it is true, otherwise false.
 * 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class schedulingCourses {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] inDegrees=new int[numCourses];
        HashMap<Integer,List<Integer>> dependencyMap=new HashMap<>();
        int count=0;

        for(int[] pr: prerequisites)//O(E)
        {
            int independent =pr[1];
            int dependent =pr[0];
            if(!dependencyMap.containsKey(independent))
            {
                List<Integer> dependentCourses=new ArrayList<>();
                dependencyMap.put(independent,dependentCourses);
            }
            dependencyMap.get(independent).add(dependent);
            inDegrees[dependent]++;
        }

        Queue<Integer> q=new LinkedList<>();

        for(int i=0;i<numCourses;i++)//O(V)
        {
            if(inDegrees[i]==0)
            {
                q.add(i);
                count++;
            }
        }

        if(count==numCourses) return true;
        if(q.isEmpty()) return false;

        while(!q.isEmpty())//O(V+E)
        {
            int independentCourse=q.poll();
            List<Integer> dependentCourses=dependencyMap.get(independentCourse);
            if(dependentCourses!=null)
            {
                for (int dependentCourse : dependentCourses) {
                    inDegrees[dependentCourse]--;
                    if(inDegrees[dependentCourse]==0)
                    {
                        q.add(dependentCourse);
                        count++;

                        
                    }
                }
                
            }
        }
        if(count==numCourses) return true;
        else return false;



        
    }
}