# eMarketShop ecommerce microservices

## Introduction
eMarketShop est une plateforme d'e-commerce innovante et scalable construite avec une architecture de microservices. Elle est conçue pour offrir une expérience utilisateur optimale tout en garantissant des performances élevées, une grande fiabilité et une sécurité renforcée.

Je suis ravi de partager mon expérience dans la conception et l'implémentation de mon application d'e-commerce innovante, eMarketShop, basée sur des microservices avec Spring. Cette aventure m'a permis de développer des compétences précieuses dans la création d'APIs REST, l'utilisation de conteneurs Docker que j'aimerais mettre à profit dans un nouveau défi professionnel.

[![Quality gate](http://4.210.225.38:9000/api/project_badges/quality_gate?project=api-gateway&token=sqb_7fa1149606b9135e62aea668135e87a5e4d15144)](http://4.210.225.38:9000/dashboard?id=api-gateway)

## Fonctionnalités
- **Gestion des produits** : Microservice pour la création, la modification et la suppression des produits.
- **Gestion des catégories de produits** : Microservice pour organiser les produits par catégories.
- **Gestion des favoris** : Microservice pour que les utilisateurs puissent sauvegarder leurs produits préférés.
- **Gestion des paiements** : Microservice pour le traitement sécurisé des paiements.
- **Gestion des commandes** : Microservice pour la création et le suivi des commandes.
- **Gestion des utilisateurs** : Microservice pour l'inscription, la connexion et la gestion des profils utilisateurs.
- **API Gateway** : Microservice pour la gestion des routages et des accès aux autres microservices.
- **Service Discovery** : Microservice pour la gestion des services entrants et sortants
- **Conig Service** : Microservice pour la gestion des configurations des autres services

## Architecture
### Microservices
- **Product Management**
- **Category Management**
- **Favorites Management**
- **Payment Management**
- **Order Management**
- **User Management**
- **API Gateway**

### Technologies Utilisées
- **APIs REST** : Spring & Spring Boot 3.3.2
- **Bases de données** : H2 DB (developpement) - PostgreSQL (Production)
- **Containerisation** : Docker
- **Gestion des configurations** : Spring Cloud Config
- **Service Discovery** : Spring Netflix Eureka
- **Tolérance aux pannes** : Resilience4j
- **Routage des services** : Spring API Gateway
- **Transfert de données et cache** : Apache Kafka et Redis
- **Logs centralisés et traces** : ELK Stack (Elasticsearch, Logstash, Kibana) et Micrometer
- **Surveillance des services** : Prometheus et Grafana
- **Orchestration des services**: Kubernetes
- **CI/CD** : Jenkins

## Qualités de l'Application
1. **Reliability and Stability** : Utilisation de Resilience4j pour la tolérance aux pannes et la résilience des services.
2. **Performance** : Optimisation des microservices pour des temps de réponse rapides et une gestion efficace des ressources.
3. **Scalability** : Déploiement de microservices avec Kubernetes pour l'auto-scaling.
4. **Security** : Sécurisation des services avec HTTPS, OAuth2/JWT et pratiques de sécurité avancées (en cours de développement).
5. **Maintainability** : Code structuré et documentation détaillée pour faciliter la maintenance et les mises à jour.
6. **Testability** : Tests unitaires et d'intégration pour assurer la qualité du code (en cours de développement).
7. **Extensibility** : Architecture modulaire permettant l'ajout facile de nouvelles fonctionnalités.
8. **Usability** : Interface utilisateur intuitive et expérience utilisateur fluide avec POSTMAN et Swaggers APIs.
9. **Portability** : Containerisation avec Docker pour déploiement sur différents environnements.
10. **Observability** : Monitoring avancé avec Prometheus, Grafana, ELK Stack et Micrometer.
11. **Compliance** : Conformité avec les normes de sécurité et de confidentialité des données.
12. **High Availability** : Déploiement multi-régions avec réplication des services pour garantir une haute disponibilité.

## Installation
### Prérequis
- Docker
- Docker Compose
- JDK 17+
- Git
- Maven
- Kubernetes
- VSCode

### Étapes d'Installation
1. Clonez le repository :
    ```sh
    git clone https://github.com/username/eMarketShop.git
    ```
2. Naviguez dans le dossier du projet :
    ```sh
    cd eMarketShop-ecommerce-micorservices
    ```

## Auteurs
- **Brice MONTHE DJEUMOU** - Ingénieur Développeur Backend Java et DevOps
- **Collaborateurs** - Liste des contributeurs

## Licence
Ce projet est sous licence libre - voir le fichier `LICENSE` pour plus de détails.

## Contact
Pour toute question, suggestion ou opportunité, n'hésitez pas à me contacter via [LinkedIn](https://www.linkedin.com/in/brice-monthe-0b64061a2/) ou par email à monthedjeumoubrice2000@gmail.com.

---

Merci d'avoir consulté mon projet eMarketShop ! Je suis impatient de recevoir vos retours et de collaborer avec vous.
