import java.util.*;

public class DynamicTeamAllocation {
    public static Map<Integer, List<Integer>> allocateProjects(List<Map<String, Integer>> projects, List<Integer> developers) {
        Collections.sort(developers);
        boolean[] used = new boolean[developers.size()];
        Map<Integer, List<Integer>> allocation = new HashMap<>();

        for (Map<String, Integer> project : projects) {
            List<Integer> team = new ArrayList<>();
            int teamSize = project.get("team_size");
            int skillRequired = project.get("skill_required");

            for (int j = 0; j < teamSize; j++) {
                int closestIndex = -1;
                int diff = Integer.MAX_VALUE;
                for (int i = 0; i < developers.size(); i++) {
                    if (!used[i] && Math.abs(developers.get(i) - skillRequired) < diff) {
                        diff = Math.abs(developers.get(i) - skillRequired);
                        closestIndex = i;
                    }
                }
                if (closestIndex == -1) return null;
                team.add(developers.get(closestIndex));
                used[closestIndex] = true;
            }
            allocation.put(project.get("id"), team);
        }
        return allocation;
    }

    public static void main(String[] args) {
        List<Map<String, Integer>> projects = new ArrayList<>();
        Map<String, Integer> p1 = new HashMap<>();
        p1.put("id", 1); p1.put("team_size", 2); p1.put("skill_required", 5);
        Map<String, Integer> p2 = new HashMap<>();
        p2.put("id", 2); p2.put("team_size", 3); p2.put("skill_required", 7);
        projects.add(p1); projects.add(p2);

        List<Integer> developers = Arrays.asList(4, 5, 6, 7, 8, 5);

        Map<Integer, List<Integer>> result = allocateProjects(projects, developers);
        System.out.println(result);
    }
}
