# TextureCraft-Back
> Back-end application for Minecraft-like texture generation

---

## Description
TextureCraft-Back est le pivot central de l'écosystème TextureCraft. 

Cette application Java agit comme le "cerveau" sémantique du projet : elle réceptionne 
les intentions de création depuis le front-end et les transforme en prompts structurés 
et optimisés pour l'API d'inférence Python.

### Liens du projet :
Front : https://github.com/PierreNicolasRey/TextureCraft-Front

API Python : https://github.com/PierreNicolasRey/TextureCraft-LoRA

---

## Architecture & Flux
L'application suit un flux séquentiel et déterministe, garantissant que chaque 
paramètre métier (matériau, couleur, style) est traduit fidèlement pour le 
modèle Stable Diffusion.

1. **Reception** : Mapping du DTO depuis Angular vers un `Record` Java.
2. **Prompt Building** : Construction d'une chaîne de caractères normalisée (tags obligatoires et optionnels).
3. **Inférence** : Appel synchrone à l'API Python (via `HttpClient5`).
4. **Proxying** : Retour des images générées en Base64 vers l'interface utilisateur.

___

## Tech Stack
- **Language** : Java 21
- **Framework** : Spring Boot 4.0.1 (Web)
- **Architecture** : Modèle "Lean" (sans persistance) pour une exécution locale rapide.
- **Tools** :
    - Maven 3.9.6
    - Jackson Databind (Gestion JSON)
    - Apache HttpClient5 (Communication REST)

---

## Configuration & Installation
### Prérequis
- **JDK 21** ou supérieur.
- **Maven** pour le build.

### Lancement
Par défaut, l'application est configurée pour communiquer avec l'API Python 
sur `http://localhost:8000`.

```bash
mvn spring-boot:run
```

---

### Endpoints
POST /api/generer_texture : Point d'entrée principal. 
Reçoit un formulaire de génération et retourne la texture résultante.

---

### Design Principles
Rich Domain Model : La logique de formatage des prompts est encapsulée directement 
dans les objets de domaine (GenerationForm), facilitant les tests unitaires et 
la maintenance.

YAGNI (You Ain't Gonna Need It) : Architecture volontairement épurée, 
sans base de données, privilégiant la vitesse de traitement et la simplicité 
de déploiement en local.

---

## Copyright

© 2026 Pierre-Nicolas Rey. Tous droits réservés. Le code et les modèles présents dans ce dépôt sont mis à disposition pour consultation technique uniquement. 
Toute reproduction ou utilisation sans autorisation préalable est interdite.