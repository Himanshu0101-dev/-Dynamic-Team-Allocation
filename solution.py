def allocate_projects(projects, developers):
    developers.sort()
    allocation = {}
    used = [False] * len(developers)

    for project in projects:
        team = []
        for _ in range(project["team_size"]):
            # pick closest available developer
            closest = None
            diff = float("inf")
            for i, d in enumerate(developers):
                if not used[i] and abs(d - project["skill_required"]) < diff:
                    diff = abs(d - project["skill_required"])
                    closest = i
            if closest is None:
                return "Not Possible"
            team.append(developers[closest])
            used[closest] = True
        allocation[project["id"]] = team
    return allocation


# Example usage
projects = [
    {"id": 1, "team_size": 2, "skill_required": 5},
    {"id": 2, "team_size": 3, "skill_required": 7}
]
developers = [4, 5, 6, 7, 8, 5]

print(allocate_projects(projects, developers))
