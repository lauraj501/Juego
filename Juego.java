import java.util.Scanner;

// Clase base del personaje
class Personaje {
    String nombre;
    int fuerza;
    int vida_hp;

    // Definir constructor
    public Personaje(String nombre, int fuerza, int vida_hp) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.vida_hp = vida_hp;
    }

    // Método para recibir daño
    public void recibirDaño(int daño) {
        vida_hp -= daño;
        if (vida_hp < 0) vida_hp = 0;
    }

    // Método para mostrar información del personaje
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Vida: " + vida_hp);
    }
}

class SuperHero extends Personaje {
    // Definir el constructor
    public SuperHero(String nombre, int fuerza, int vida_hp) {
        super(nombre, fuerza, vida_hp);
    }

    // Método para atacar
    public void atacar(Villano villano) {
        System.out.println(nombre + " ataca a " + villano.nombre);
        villano.recibirDaño(fuerza); //Método recibirDaño
    }

    // Método para defenderse
    public void defender(int daño) {
        int dañoRecibido = daño / 2; // Reduce el daño a la mitad
        recibirDaño(dañoRecibido); // llama al método para aplicar el daño reducido
        System.out.println(nombre + " se defiende y recibe solo " + dañoRecibido + " de daño.");
    }

    // Método para aumentar poderes
    public void aumentarPoderes() {
        fuerza += 10; // Aumenta la fuerza
        System.out.println(nombre + " ha aumentado su fuerza a " + fuerza + "!");
    }

    // Método para recuperarse
    public void recuperarse() {
        vida_hp += 20; // Recupera vida
        System.out.println(nombre + " se recupera y ahora tiene " + vida_hp + " de vida.");
    }
}

class Villano extends Personaje {
    // Definir el constructor
    public Villano(String nombre, int fuerza, int vida_hp) {
        super(nombre, fuerza, vida_hp);
    }

    // Método para atacar
    public void atacar(SuperHero superHero) {
        System.out.println(nombre + " ataca a " + superHero.nombre);
        superHero.recibirDaño(fuerza);
    }

    // Método para aumentar poderes
    public void aumentarPoderes() {
        fuerza += 10; // Aumenta la fuerza
        System.out.println(nombre + " ha aumentado su fuerza a " + fuerza + "!");
    }

    // Método para recuperarse
    public void recuperarse() {
        vida_hp += 20; // Recupera vida
        System.out.println(nombre + " se recupera y ahora tiene " + vida_hp + " de vida.");
    }

    // Método para defenderse
    public void defender(int daño) {
        int dañoRecibido = daño / 2; // Reduce el daño a la mitad
        recibirDaño(dañoRecibido);
        System.out.println(nombre + " se defiende y recibe solo " + dañoRecibido + " de daño.");
    }
}

public class Juego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Definir superhéroes
        SuperHero[] heroes = {
            new SuperHero("Thor", 50, 100),
            new SuperHero("Iron Man", 40, 80),
            new SuperHero("Spider-man", 45, 90)
        };

        // Definir villanos
        Villano[] villanos = {
            new Villano("Harley Quinn", 45, 85),
            new Villano("Cruella", 30, 70),
            new Villano("Malefica", 40, 75)
        };

        // Mostrar opciones de superhéroes
        System.out.println("Elija un superhéroe:");
        for (int i = 0; i < heroes.length; i++) {
            System.out.println((i + 1) + ". " + heroes[i].nombre + " (Fuerza: " + heroes[i].fuerza + ", Vida: " + heroes[i].vida_hp + ")");
        }
        int eleccionHeroe = scanner.nextInt() - 1;

        // Mostrar opciones de villanos
        System.out.println("Elija un villano:");
        for (int i = 0; i < villanos.length; i++) {
            System.out.println((i + 1) + ". " + villanos[i].nombre + " (Fuerza: " + villanos[i].fuerza + ", Vida: " + villanos[i].vida_hp + ")");
        }
        int eleccionVillano = scanner.nextInt() - 1;

        // Seleccionar personajes elegidos
        SuperHero heroe = heroes[eleccionHeroe];
        Villano villano = villanos[eleccionVillano];

        boolean heroeDefendido = false;
        boolean villanoDefendido = false;

        // Simulación de batalla
        while (heroe.vida_hp > 0 && villano.vida_hp > 0) {
            System.out.println("\nTurno del superhéroe:");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Aumentar Poderes");
            System.out.println("4. Recuperarse");
            System.out.print("Elija una acción: ");
            int accionHeroe = scanner.nextInt();

            switch (accionHeroe) {
                case 1:
                    if (villanoDefendido) {
                        // Si el villano se defendió, el héroe ataca pero el daño se reduce
                        villano.defender(heroe.fuerza);
                        villanoDefendido = false; // Resetea la defensa
                    } else {
                        heroe.atacar(villano);
                    }
                    break;
                case 2:
                    heroeDefendido = true; // Marca que el héroe se defendió
                    break;
                case 3:
                    heroe.aumentarPoderes();
                    break;
                case 4:
                    heroe.recuperarse();
                    break;
                default:
                    System.out.println("Acción no válida.");
            }

            // Mostrar estadísticas
            heroe.mostrarInfo();
            villano.mostrarInfo();

            // Verificar si el villano sigue vivo
            if (villano.vida_hp > 0) {
                System.out.println("\nTurno del villano:");
                System.out.println("1. Atacar");
                System.out.println("2. Defender");
                System.out.println("3. Aumentar Poderes");
                System.out.println("4. Recuperarse");
                System.out.print("Elija una acción: ");
                int accionVillano = scanner.nextInt();

                switch (accionVillano) {
                    case 1:
                        if (heroeDefendido) {
                            // Si el héroe se defendió, el villano ataca pero el daño se reduce
                            heroe.defender(villano.fuerza);
                            heroeDefendido = false; // Resetea la defensa
                        } else {
                            villano.atacar(heroe);
                        }
                        break;
                    case 2:
                        villanoDefendido = true; // Marca que el villano se defendió
                        break;
                    case 3:
                        villano.aumentarPoderes();
                        break;
                    case 4:
                        villano.recuperarse();
                        break;
                    default:
                        System.out.println("Acción no válida.");
                }
            }

            // Mostrar estadísticas después del turno del villano
            heroe.mostrarInfo();
            villano.mostrarInfo();
        }

        // Resultado de la batalla
        if (heroe.vida_hp > 0) {
            System.out.println("\n" + heroe.nombre + " ha ganado la batalla!");
        } else {
            System.out.println("\n" + villano.nombre + " ha ganado la batalla!");
        }

        scanner.close();
    }
}
