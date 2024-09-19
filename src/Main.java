import repository.impl.ClientRepositoryImpl;
import repository.impl.ComposantRepositoryImpl;
import repository.impl.ProjectRepositoryImpl;
import service.impl.ClientServiceImpl;
import service.impl.ComposantServiceImpl;
import service.impl.ProjectServiceImpl;
import ui.ClientUi;
import ui.ComposantUi;
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

//        composant
        ComposantRepositoryImpl composantRepository = new ComposantRepositoryImpl();
        ComposantServiceImpl composantService = new ComposantServiceImpl(composantRepository);
        ComposantUi composantUi = new ComposantUi(composantService);
//        composantUi.menu();
    }
}