package service.impl;

import entity.Client;
import entity.Project;
import repository.ClientRepository;
import repository.ProjectRepository;
import service.ProjectService;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ClientRepository clientRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * @param project
     */
    @Override
    public void createProject(Project project, Client client) {
        Long clientId = clientRepository.addClient(client);

        project.setClientId(clientId);

        projectRepository.saveProject(project);

        System.out.println("Projet ajouté avec succès.");
    }

    /**
     * @return
     */
    @Override
    public List<Project> getProjects() {
        return List.of();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Project> getProject(Long id) {
        return Optional.empty();
    }
}
