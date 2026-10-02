
class EventEmitter {
    constructor() {
        this.events = new Map();
    }

    subscribe(eventName, callback) {
        if (!this.events.has(eventName)) {
            this.events.set(eventName, []);
        }

        this.events.get(eventName).push(callback);

        return {
            unsubscribe: () => {
                let callbacks = this.events.get(eventName);
                let index = callbacks.indexOf(callback);
                callbacks.splice(index, 1);
            }
        };
    }

    emit(eventName, args = []) {
        if (!this.events.has(eventName)) {
            return [];
        }

        let result = [];

        for (let callback of this.events.get(eventName)) {
            result.push(callback(...args));
        }

        return result;
    }
}