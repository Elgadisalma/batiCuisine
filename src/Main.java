import repository.impl.ClientRepositoryImpl;
import repository.impl.ProjectRepositoryImpl;
import service.impl.ClientServiceImpl;
import service.impl.ProjectServiceImpl;
import ui.ClientUi;
import ui.ProjectUi;

public class Main {
    public static void main(String[] args) {
//        clients
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        ClientServiceImpl clientService = new ClientServiceImpl(clientRepository);

        ClientUi clientUi = new ClientUi(clientService);
//        clientUi.menu();


//        projects
        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl(projectRepository, clientRepository);

        ProjectUi projectUi = new ProjectUi(clientService, projectService);
        projectUi.menu();
    }
}