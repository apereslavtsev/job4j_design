package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsBuh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Buh"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsBuh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        store.add(new Role("1", "Prog"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Buh"));
    }

    @Test
    public void whenReplaceThenRoleIsProg() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        store.replace("1", new Role("1", "Prog"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Prog"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        store.replace("10", new Role("10", "Prog"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Buh"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsBuh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Buh"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Buh"));
    }

}