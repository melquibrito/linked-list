package linkedlist;

/*We're using the method "compareTo" from the Interface "Comparable" in order 
to flexibilize the sorting method delegating the comparing method to the class 
defining the type of the list*/
public class LinkedList<T extends Comparable> {

    private class Element {

        Element(T object, Element next) {
            this.object = object;
            this.next = next;
        }

        T object;
        Element next;

    }

    private Element first = null; //Points to the first element of the list

    //Returns the size of the list
    public int size() {
        int size = 0;
        Element node = first;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    //Returns true if there's no element in the list
    public boolean isEmpty() {
        return first == null;
    }

    //Adds an object to the list placing it at the end of the list
    public void add(T object) {
        Element node = new Element(object, null);
        if (isEmpty()) {
            first = node;
        } else {
            Element aux = first;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = node;
        }
    }

    //Removes an object from the list if it is in it
    public boolean remove(T object) {
        Element previousNode, node = first;
        while (node != null && node.object != object) {
            previousNode = node;
            node = node.next;
        }
        if (node == null) {
            return false;
        }
        if (node == first) {
            first = first.next;
        } else {
            previousNode = node.next;
        }
        return true;
    }

    //Returns the object in the position indicated in the parameters 
    public T get(int index) {
        if (getElement(index) != null) {
            return getElement(index).object;
        }
        return null;
    }

    //Adds new object at the first position of the list
    public void addToTheBeginning(T object) {
        Element added = new Element(object, null);
        if (first == null) {
            first = added;
        } else {
            added.next = first;
            first = added;
        }
    }

    //Removes the first element from the list
    public void removeTheFirst() {
        if (first != null) {
            first = first.next;
        }
    }

    //Removes the last element from the list
    public boolean removeTheLast() {
        if (first != null) {
            Element node = first;
            if (node.next != null) {
                if (node.next.next != null) {
                    while (node.next.next != null) {
                        node = node.next;
                    }
                    node.next = null;
                } else {
                    node.next = null;
                }
            } else {
                first = null;
            }
            return true;
        }
        return false;
    }

    //Returns the element in the position indicated in the parameters
    private Element getElement(int index) {
        if (!isEmpty()) {
            if (index == 0) {
                return first;
            } else {
                Element node = first;
                for (int i = 1; i < this.size(); i++) {
                    node = node.next;
                    if (i == index) {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    //Removes elements by their index
    public boolean remove(int index) {
        if (index < this.size()) {
            if (index == 0) {
                first = first.next;
            } else {
                Element previous = this.getElement(index - 1), node = this.getElement(index);
                previous.next = node.next;
            }
            return true;
        }
        return false;
    }

    //Returns the object that, when parsed to string, matches the string indicated in the parameters
    public T get(String string) {
        if (first != null) {
            Element node = first;
            int i = 0;
            while (i < this.size()) {
                if (node.object.toString().equalsIgnoreCase(string)) {
                    return node.object;
                }
                node = node.next;
                i++;
            }
        }
        return null;
    }

    //Adds objects calling the sort method afterwards
    public synchronized void addNeatly(T object) {
        add(object);
        sort();
        //The following algrithm can be used without the need of a sort method
        //If always used instead of any other adding method, the list will always be sorted
        /*Element added = new Element(object, null);
        if (!isEmpty()) {
            int index = 0;
            Element node = first;
            while (node != null) {
                if (object.compareTo(node.object) < 0) {
                    break;
                }
                node = node.next;
                index++;
            }
            if (index == 0) {
                added.next = first;
                first = added;
            } else {
                added.next = getElement(index);
                getElement(index - 1).next = added;
            }
        } else {
            first = added;
        }*/
    }

    //Sorts the list from the beginning
    public void sort() {
        if (!isEmpty() && size() > 1) {
            Element comesFirst = first;
            for (int i = 1; i < this.size(); i++) {
                if (comesFirst.object.compareTo(get(i)) > 0) {
                    comesFirst = getElement(i);
                }
            }
            Element previous, next = comesFirst.next;
            if (comesFirst != first) {
                previous = getElement(indexOf(comesFirst.object) - 1);
                comesFirst.next = first;
                previous.next = next;
                first = comesFirst;
            }

            int startingPoint = 2;
            for (int i = 1; i < this.size(); i++) {
                comesFirst = getElement(i);
                for (int j = startingPoint; j < this.size(); j++) {
                    if (comesFirst.object.compareTo(get(j)) > 0) {
                        comesFirst = getElement(j);
                    }
                }
                if (comesFirst != getElement(i)) {
                    next = comesFirst.next;
                    previous = getPrevious(comesFirst);
                    Element previous_i = getElement(i - 1);
                    comesFirst.next = getElement(i);
                    previous.next = next;
                    previous_i.next = comesFirst;
                }
                startingPoint++;

            }
        }
    }
    
    //Returns the previous element of the element passed in the parameters
    private Element getPrevious(Element element) {
        Element previous = null, node = first;
        if(!isEmpty() && element != first) {
            while (first.next != null && node != element) {
                previous = node;
                node = node.next;
            }
        }
        return previous;
    }
    
    //Returns the index of the object if it's in the list
    public int indexOf(T object) {
        int index = -1;
        if (!isEmpty()) {
            Element node = first;
            int i = 0;
            while (node != null) {
                if (node.object.equals(object)) {
                    return i;
                }
                node = node.next;
                i++;
            }
        }
        return index;
    }

    /*Returns the index of the object if it equals or is above 
    the starting point of the searching. It helps prevent NullPointerException
    during the sorting process in the sort method once the list may accept 
    repeaded objects when you aren't using the adding method 
    "addPreventingRepetition"*/
    private int indexOf(int startingPoint, T object) {
        int index = -1;
        if (!isEmpty()) {
            Element node = getElement(startingPoint);
            int i = startingPoint;
            while (node != null) {
                if (node.object.equals(object)) {
                    return i;
                }
                node = node.next;
                i++;
            }
        }
        return index;
    }

    //Returns true if the list contains the object passed in the parameters
    public boolean contains(T object) {
        return indexOf(object) > -1;
    }

    /*Adds objects by setting it to the indicated position in the list 
    replacing the object currently ocupying the same position*/
    public void set(int index, T object) {
        if (index >= 0) {

            if (index < size()) {
                Element added = new Element(object, null);
                if (index == 0) {
                    added.next = first.next;
                    first = added;
                }else{
                    added.next = getElement(index).next;
                    getElement(index - 1).next = added;
                }
            } else {
                add(object);
            }
        }
    }

    //Adds objects to the list preventing repetition
    public void addPreventingRepetition(T object) {
        if (!contains(object)) {
            add(object);
        }
    }

    //Adds objects to the beginning of the list preventing repetition
    public void addToTheBeginningPreventingRepetition(T object) {
        if (!contains(object)) {
            addToTheBeginning(object);
        }
    }
}
