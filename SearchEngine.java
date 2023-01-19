import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
 class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String s="";
    ArrayList<String> search= new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Simple Search Engine");
        } else if (url.getPath().equals("/search")) {
            ArrayList<String> searchresults= new ArrayList<String>();
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for(int i=0;i<search.size();i++){
                        if(search.get(i).contains(parameters[1])){
                            searchresults.add(search.get(i));
                        }
                    }
                    /*num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1]);*/
                }
                return String.format("The search results are"+searchresults);
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    search.add(parameters[1]);
                    return String.format("String added to search engine", parameters[1]);
                }
            }
            return "404 Not Found!";
        }
    }
}   
class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}