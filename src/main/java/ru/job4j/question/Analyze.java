package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);

        /* count deleted users*/
        Set<User> dataPrev = new HashSet<>(previous);
        dataPrev.removeAll(current);
        info.setDeleted(dataPrev.size());

        /* count added users */
        Set<User> dataCurrent = new HashSet<>(current);
        dataCurrent.removeAll(previous);
        info.setAdded(dataCurrent.size());

        /* count changed users */
        Map<Integer, String> previousMap = dataPrev.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        Map<Integer, String> currentMap = dataCurrent.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (int id: previousMap.keySet()) {
            String currentValue = currentMap.get(id);
            if (currentValue != null
                && !currentValue.equals(previousMap.get(id))) {
                info.setChanged(info.getChanged() + 1);
            }
        }

        info.setChanged(info.getChanged());
        info.setAdded(info.getAdded() - info.getChanged());
        info.setDeleted(info.getDeleted() - info.getChanged());

        return info;
    }

}