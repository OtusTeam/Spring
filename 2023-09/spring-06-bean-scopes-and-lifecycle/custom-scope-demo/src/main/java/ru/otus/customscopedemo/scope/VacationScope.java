package ru.otus.customscopedemo.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import ru.otus.customscopedemo.vacation.VacationCalendar;

import java.util.HashMap;
import java.util.Map;


public class VacationScope implements Scope {

    private final Map<String, Runnable> destructionCallbacks;
    private final Map<String, Object> contextualObjects;

    private final VacationCalendar vacationCalendar;

    public VacationScope(VacationCalendar vacationCalendar) {
        this.destructionCallbacks = new HashMap<>();
        this.contextualObjects = new HashMap<>();
        this.vacationCalendar = vacationCalendar;
    }

    // This is the central operation of a Scope, and the only operation that is absolutely required.
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        var res = resolveContextualObject(name);
        if (res != null) {
            return res;
        }

        if (vacationCalendar.isCanTakeVacation()) {
            res = objectFactory.getObject();
            contextualObjects.put(name, res);
            return res;
        }

        throw new VacationDoesNotAvailableException();
    }

    // Note: This is an optional operation. Implementations may throw UnsupportedOperationException
    // if they do not support explicitly removing an object.
    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return contextualObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        destructionCallbacks.put(name, runnable);
    }

    @Override
    public Object resolveContextualObject(String name) {
        if (!vacationCalendar.isCanTakeVacation() && contextualObjects.containsKey(name)) {
            remove(name);
        }
        return contextualObjects.get(name);
    }

    @Override
    public String getConversationId() {
        return null;
    }

    public void onDestroy() {
        destructionCallbacks.values().forEach(Runnable::run);
    }
}
