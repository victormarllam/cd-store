# Cd Projekt Store Exercise
Java 11
## How to build
#### Using Gradle
Execute following terminal commands.
```
./gradlew build
./gradlew run
```
#### Inside IntelliJ
Just open the project and play the main program.
#### Using Kubernetes
1. Install MiniKube https://minikube.sigs.k8s.io/docs/start/

2. Install Docker Desktop

3. Execute following command inside root project, where the Dockerfile exists
```
docker build --build-arg JAR_FILE=build/libs/mc_cd_catalog-0.0.1-SNAPSHOT.jar -t victor/cd-store .
```
4. Execute following commands inside root project, regarding MiniKube and Kubernetes
```
minikube start
minikube image load victor/cd-store
kubectl apply -f .\kubernetes\mc_cd_catalog\deployment.yaml
minikube service cd-store --url
```
The first command starts a k8s single-node cluster.

The second command makes the docker image available for minikube.

The third command applies a deployment and a service to let k8s manage and expose instances.

Fourth command will print the URL to communicate with the service.
### Hexagonal Architecture:
The Architecture approach is based on Hexagonal Architecture - Ports and Adapters
Architecture, also tried to be Domain-Driven.

- Inside the resources folder you will see an architecture diagram representing the CatalogProductService.
You may notice that no arrow goes from the domain to the outside.
```
CatalogProductServiceArchitecture.png
```
You will see the following folder structure inside the main folder, foreach Domain:
- **domain**: Where the core-domain of our business is built. Following Hexagonal Architecture, nothing inside the domain
  should now anything about the outside (HttpServer, database, ...)
    - **model**: Where data models from our business are written.
    - **ports**:
        - incoming ports expose interfaces to the outside to communicate with the domain.
        - outcoming ports expose interfaces used by the domain to communicate with the outside.
        - Following this approach, we can make our services communicate with a JPA Repository in a decoupled way.
    - **services**: Where our business logic - use cases are implemented.
- **infrastructure**: Here we will find the implementation about the outside or details that the business
  logic shouldn't know about, such as the HTTP Server and Databases.

Even with my implementation, you may find coupling between Domain-Spring and Domain-Hibernate.
Looking for a cleaner approach, this must be solved in future iterations.

- You may also notice that Product domain doesn't follow this architecture due to time constraints.

### Domain Model:
Domain Model to support some of the required operations is
```
Product #Contains the information of a product.
CatalogProduct #Represents the information of a product in a store catalog.
```
`money` and `discount` attributes inside `CatalogProduct` doesn't have a concrete use
for this application and exists as a placeholder.
### Business Logic:
Business Logic is performed and is composed by CatalogProductService and Product Use Cases
```
Product addProduct(Product product);
void deleteProductById(Integer idProduct);
CatalogProduct createOrUpdateProductToCatalog(CatalogProduct catalogProduct);
Page<CatalogProduct> findByTitleLike(String title, Pageable pageable);
void deleteProductFromCatalog(int id);
```
- Due to time constraints, developing the back-office drafting feature and shopping cart feature wasn't 
possible for me.
### Database:
H2 is choosed as in-memory database for this application due to time constraints but a consideration must be taken:
- For a productive environment I would have chosen a MySql database with one primary database and at least one replica database to be able to scale out reads.
### Hibernate and Cache:
- Hibernate integration was done, to use it as ORM, in order to make easier the communication and
translation from database data to java data.
- Hibernate Second Level Cache was integrated in order to improve performance regarding
the persistence layer cache.
- Apache Ignite was choosed as Hibernate Second Level Cache Implementation for the following reasons:
  - Apache Ignite allows you to create Replicated and/or Distributed Cache servers in order to scale out cache.
  - Replicated Cache was choosed because it works better with a small cache and with far more read operations
than write operations, and thats the case for Hibernate Cache.
  - Is Open Source and the documentation is great.
### Unit Testing:
Unit Testing was made by using mockito to work with mocks, jupiter and assertJ as assertion library.
Tests are written following this naming convention:
`Given_SomeSituation_When_aMethodIsExecuted_Then_SomethingHappens`.

And the implementation is as follows:
```
Given_When_Then() {
    //Declaration of test representative needed variables (Non representative has the ANY prefix).
    //Mocking methods.
    //Performing method to test.
    //Assertions making.
}
```
Unit Testing was performed just to `CatalogProductService` as a representative example due to time constraints.
### Kubernetes:
Regarding deployment and orchestration, Kubernetes + Docker was choosed as a preferred option
in order to be able to easily deploy to any cloud environment.
`Dockerfile` contains the code to build an image and `kubernetes/mc_cd_catalog/deployment.yaml` contains the Deployment 
and Service information.
### Security:
An HttpBasic Authentication, managed by `SecurityConfiguration` was choosed in order to protect `/internal` endpoints
that should be used just for employees in the back-office.
Roles are managed by a simple enum and Users are hard-coded in-memory to test easily.

### Postman
You will find an exported postman collection to test the endpoints easily.

