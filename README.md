# LibrarySearchLibrary

LibrarySearchLibrary is a part of the project Library Micro Services. This module is a server  to request information from a web services in microservices environment.
SOAP: Publishers / REST Books

## Installation

Project created in Maven, all the dependencies are in a .pom document.

Compile the code but first check the Server Url in the following class:

```
public class EntryPublisher {

    private static final int port = 8080;
    private static final String uri = "/library/";
    private static final String url = "http://localhost:" + port + uri;


AND 

public class RequestRest {
    Client client = Client.create();
    String urlClient = "http://localhost:8091/books/search/list";
    
AND

    public String getPublisher (String publisherName) throws Exception_Exception, MalformedURLException {
        URL url = new URL ("http://localhost:8092/publishers/publisher?wsdl");
        QName qname = new QName("http://publisher.search.library.com/", "PublisherImpService");
        Service service = Service.create(url, qname);
        PublisherInterface publisher = service.getPort(PublisherInterface.class);
    

```
Update URL depending on server configuration

Additonally update the txt repo in:
```
    public boolean isUserAuthenticated(String authString){
..........

        String fileName = "src/main/webapp/WEB-INF/users.txt";

```

## Contributing
Academic Project for McGill University. No contributing is requested. 

## License
[MIT](https://choosealicense.com/licenses/mit/)