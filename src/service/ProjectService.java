package service;

import entity.Client;
import entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void createProject(Project project, Client client);
    void updateProject(Project project);
    void annulerProjet(Long id);
    Optional<Project> getProject(Long id);
}
