package repository;

import entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void saveProject(Project project);
    Optional<Project> getProject(Long id);
    List<Project> getAllProjects();
}
