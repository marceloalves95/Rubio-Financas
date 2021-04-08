# Rubio_Financas

### Descrição do Projeto

> Aplicativo feito em Kotlin usando os principios de MVVM, Clean Architecture, Room, Coroutines e Live Data.

### Brands
- Receita
* [x] Cadastro de Receitas
* [x] Tela de Receitas
- Despesa
- Master

### Instalação

Para começar, inclua as seguintes dependências adicionando ao `build.gradle` do módulo do projeto e após isso atualize o `Gradle`:

```groovy
plugins{
   id 'kotlin-kapt'
   ...
}

buildFeatures{
        //ViewBinding
        viewBinding true
    }

dependencies {
    //Implementação das bibliotecas usadas neste projeto
    
    //Lifecycle
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0'
    //SwipeRefreshLayout
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    //Biblioteca CircleImageView
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    
    //Room
    def room_version = "2.2.6"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    implementation "androidx.room:room-ktx:$room_version"
    testImplementation "androidx.room:room-testing:$room_version"
    
}
```
### Telas do aplicativo🚧 

Em construção...



