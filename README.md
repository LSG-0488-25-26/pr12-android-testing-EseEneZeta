# PR12 - Android Testing (Mateo Achá)

Aquest projecte consisteix en la implementació de tests unitaris i instrumentals per a una aplicació Android basada en Jetpack Compose i seguint el patró d'arquitectura MVVM.

## Descripció del projecte
L'objectiu principal ha estat completar la lògica de l'aplicació i assegurar el seu correcte funcionament mitjançant diferents nivells de testing:

1. **MVVM i LiveData**: S'ha completat el `MainViewModel` per gestionar estats complexos com el TriStateCheckbox, l'estat del Wi-Fi, la selecció de ràdio buttons i la lògica del Snackbar.
2. **MainView**: S'ha vinculat la interfície amb el ViewModel, eliminant els TODOs originals i afegint `testTag` per facilitar el testing.

## Apartat de Testing

### Unit Testing
S'han creat tests per a tota la lògica del ViewModel, assegurant que cada acció (toggles, seleccions, sliders) actualitzi l'estat correctament.
- **Fitxer**: `MainViewModelTest.kt`
- **Comandament**: `./gradlew testDebugUnitTest`

![Resultat Unit Tests](docs/images/%7BF9B41F19-B68D-43DC-80CE-3F918A99711B%7D.png)

---

### UI Instrumental Testing
Tests realitzats sobre l'emulador per verificar la interacció de l'usuari amb els components de la interfície.
- **Fitxer**: `MainViewTest.kt`
- **Comandament**: `./gradlew connectedDebugAndroidTest`

![Resultat UI Tests](docs/images/Captura%20de%20pantalla%202026-04-29%20195619.png)

## Conclusió
L'aplicació compleix amb tots els requisits de l'enunciat, mantenint el disseny original però amb una arquitectura robusta i una cobertura de tests completa tant en lògica com en interfície.

---
**Realitzat per:** Mateo Achá
