Android Technical Roadmap: Fundamentos y Clean Architecture
Este repositorio contiene la implementación progresiva de una aplicación Android desarrollada en Java, utilizando Gradle Kotlin DSL (.kts) para la gestión de dependencias y siguiendo principios de Clean Architecture.

Conceptos Clave del Repositorio
Navegación y Comunicación
registerForActivityResult: Implementación del estándar moderno para el intercambio de datos entre actividades, eliminando el uso de APIs depreciadas.

Fragments: Gestión de fragmentos mediante el patrón newInstance y paso de argumentos vía Bundle para asegurar la persistencia tras la recreación de la vista.

Intents: Navegación y paso de datos básicos entre componentes del sistema.

Persistencia y Seguridad
Room Database: Implementación de persistencia local con entidades y DAOs.

Cifrado de Datos: Aplicación de técnicas de encriptación en campos específicos de la base de datos para proteger información sensible del usuario.

SharedPreferences: Uso de almacenamiento clave-valor para la persistencia de preferencias de la aplicación.

UI y Adaptabilidad
Recursos Dimensionados: Uso de dimens.xml para manejar medidas en dp y sp, asegurando consistencia visual.

Diseño Adaptativo: Implementación de layouts alternativos para modo Horizontal (Land) y configuraciones específicas para Tablets (100dp) frente a Teléfonos (50dp).

Internacionalización: Soporte completo para idiomas Español e Inglés mediante recursos de texto (strings.xml).

Arquitectura y Clean Code
MVP (Model-View-Presenter): Desacoplamiento de la lógica de presentación de la actividad.

Capas de Software: Separación estricta de responsabilidades en capas de UI, Dominio (Casos de Uso) y Datos (Repositorios).

SOLID: Aplicación de principios de diseño para lograr un código mantenible, testeable y escalable.

Product Flavors: Configuración de variantes de compilación para personalizar recursos y textos según la versión de la app.
