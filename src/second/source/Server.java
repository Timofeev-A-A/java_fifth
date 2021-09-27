package second.source;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends EquationImpl {

    public static void main(String[] args) {
        try {
            EquationImpl obj = new EquationImpl();

            Registry registry = LocateRegistry.createRegistry(2732);

            Equation stub = (Equation) UnicastRemoteObject.exportObject(obj, 0);

            registry.bind("equation", stub);
            System.err.println("Server ready");

        }
        catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
