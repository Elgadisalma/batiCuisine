import repository.impl.ClientRepositoryImpl;
import repository.impl.ComposantRepositoryImpl;
import repository.impl.DevisRepositoryImpl;
import repository.impl.ProjectRepositoryImpl;
import service.DevisService;
import service.impl.ClientServiceImpl;
import service.impl.ComposantServiceImpl;
import service.impl.DevisServiceImpl;
import service.impl.ProjectServiceImpl;
import ui.*;

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
        ProjectUi projectUi = new ProjectUi(clientService, projectService, clientUi);
        projectUi.menu();



//        composant
        ComposantRepositoryImpl composantRepository = new ComposantRepositoryImpl();
        ComposantServiceImpl composantService = new ComposantServiceImpl(composantRepository);
        ComposantUi composantUi = new ComposantUi(composantService);
//        composantUi.menu();


        // DevisUi et CalculUi
        DevisRepositoryImpl devisRepository = new DevisRepositoryImpl();
        DevisServiceImpl devisService = new DevisServiceImpl(devisRepository);
        DevisUi devisUi = new DevisUi(projectService,devisService);
        CalculUi calculUi = new CalculUi(projectService, composantService, projectUi, devisUi);

        // Démarrer le menu principal de CalculUi
//        calculUi.menu();


    }
}