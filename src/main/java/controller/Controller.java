package controller;

import controller.actions.Action;
import controller.actions.ActionArgs;
import view.SearchView;
import java.net.http.HttpClient;

public class Controller {
    private final HttpClient httpClient;
    private final SearchView searchView;
    private final Factory factory = new Factory();

    public Controller(SearchView searchView) {
        this.searchView = searchView;
        this.httpClient = HttpClient.newHttpClient();
    }
    
    private String getArgs(String[] action) {
    	try {
            return action[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    public Action getAction() {
        String[] action = searchView.waitAction().split(" ", 2);
        String actionName = action[0];

        return factory.createObject(actionName, new ActionArgs(httpClient, getArgs(action)));       
    }
}
