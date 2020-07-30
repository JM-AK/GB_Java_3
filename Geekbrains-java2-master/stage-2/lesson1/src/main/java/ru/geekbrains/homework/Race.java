package ru.geekbrains.homework;

import ru.geekbrains.homework.barrier.Barrier;
import ru.geekbrains.homework.barrier.Track;
import ru.geekbrains.homework.barrier.Wall;
import ru.geekbrains.homework.participant.Cat;
import ru.geekbrains.homework.participant.Human;
import ru.geekbrains.homework.participant.Movable;

public class Race {

    public static void main(String[] args) {

        Movable[] participatns = {
                new Cat("Кот1", 10, 20),
                new Human("Человек1", 15, 30),
                new Cat("Кот2", 7, 23),
                new Human("Человек2", 12, 31),
                new Cat("Кот3", 5, 24),
                new Human("Человек3", 11, 35),
        };

        Barrier[] barriers = {
            new Wall(5),
            new Track(10),
            new Wall(10),
            new Track(20),
            new Wall(11),
            new Track(33)
        };

        for(Movable participant : participatns) {
            for (int i = 0; i < barriers.length; i++) {
                if (barriers[i] instanceof Wall) {
                    participant.jump((Wall)barriers[i]);
                    if (participant.jump((Wall)barriers[i])) {
                        System.out.println(participant + " участник cмог перепрыгнуть");
                    } else {
                        participant.setDroppedOut(true);
                    }
                } else if (barriers[i] instanceof Track) {
                    if (participant.walk((Track)barriers[i])) {
                        System.out.println(participant + " участник cмог пробежать");
                     }else {
                        break;
                    }
                } else {
                    if (participant.overcome(barriers[i])) {
                        System.out.println(participant + " участник cмог преодолеть");
                    }else {
                        break;
                    }
                }
            }
        }


        for (int i = 0; i < barriers.length; i++) {
            for(Movable participant : participatns) {
                if (participant.isDroppedOut()) {
                    continue;
                }

                if (barriers[i] instanceof Wall) {
                    participant.jump((Wall)barriers[i]);
                    if (participant.jump((Wall)barriers[i])) {
                        System.out.println(participant + " участник cмог перепрыгнуть");
                    } else {
                        participant.setDroppedOut(true);
                    }
                } else if (barriers[i] instanceof Track) {
                    if (participant.walk((Track)barriers[i])) {
                        System.out.println(participant + " участник cмог пробежать");
                    }else {
                        participant.setDroppedOut(true);
                    }
                } else {
                    if (participant.overcome(barriers[i])) {
                        System.out.println(participant + " участник cмог преодолеть");
                    }else {
                        participant.setDroppedOut(true);
                    }
                }
            }
        }

    }
}
