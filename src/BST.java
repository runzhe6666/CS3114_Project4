import java.util.Stack;

/**
 * The underlying BST that supports the Rectangle1 program.
 * 
 * @author Samuel Turner <samt5>
 * @author Zuri Wong <zuriw@vt.edu>
 * 
 * @version 2017.09.16
 * 
 * @param <T>
 *            The type that is being put into the Tree. It must implement
 *            Comparable.
 */
public class BST<T extends Comparable<? super T>> {
    private BSTNode<T> root;

    /**
     * Constructs an empty tree.
     */
    public BST() {
        root = null;
    }

    /**
     * Insert into the tree.
     *
     * @param x
     *            the item to insert.
     */
    public void insert(T x) {
        if (x == null) {
            return;
        }
        root = insert(x, root);
    }

    /**
     * Remove the specified value from the tree.
     *
     * @param x
     *            the item to remove.
     * @throws ItemNotFoundException
     *             if x is not found.
     * @return The value that was removed.
     */
    public T remove(T x) {
        Tuple<BSTNode<T>, T> result = remove(x, root);
        root = result.getValue1();
        return result.getValue2();
    }

    /**
     * Find an item in the tree.
     *
     * @param x
     *            the item to search for.
     * @return the matching item or null if not found.
     */
    public T find(T x) {
        if (x == null) {
            return null;
        }
        return elementAt(find(x, 0, root));
    }

    /**
     * Finds the ith copy of an element in the tree.
     * 
     * @param x
     *            the item to find
     * @param i
     *            the index of the element to find
     * @return The element found.
     */
    public T find(T x, int i) {
        if (x == null) {
            return null;
        }
        return elementAt(find(x, i, root));
    }

    /**
     * Helper function that finds the ith copy of an element in the tree.
     * 
     * @param x
     *            the item to find
     * @param i
     *            the index of the element to find
     * @param node
     *            the node that is being evaluated
     * @return The element found.
     */
    private BST<T>.BSTNode<T> find(T x, int i, BST<T>.BSTNode<T> node) {
        if (node == null) {
            return null; // Not found
        }
        else if (x.compareTo(node.getElement()) < 0) {
            // Search in the left subtree
            return find(x, i, node.getLeft());
        }
        else if (x.compareTo(node.getElement()) > 0) {
            // Search in the right subtree
            return find(x, i, node.getRight());
        }
        else {
            if (i == 0) {
                return node;
            }
            return find(x, i - 1, node.getRight());
        }
    }

    /**
     * Finds the depth of the given value in the tree. Returns 0 if the value is
     * not in the tree.
     * 
     * @param value
     *            The value being searched.
     * @param index
     *            Which element of this name we are accessing (for repetition).
     * @return The depth of the element in the tree.
     */
    public int getDepth(T value, int index) {
        if (value == null) {
            return 0;
        }
        return getDepth(value, root, index);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Internal method to get element value stored in a tree node, with safe
     * handling of null nodes.
     *
     * @param node
     *            the node.
     * @return the element field or null if node is null.
     */
    private T elementAt(BSTNode<T> node) {
        return (node == null) ? null : node.getElement();
    }

    /**
     * Internal method to insert a value into a subtree.
     *
     * @param x
     *            the item to insert.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BSTNode<T> insert(T x, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<T>(x);
        }
        else if (x.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(x, node.getLeft()));
        }
        else {
            node.setRight(insert(x, node.getRight()));
        }
        return node;
    }
    
    public void inOrderIterPrint() {
    	//T[] myArray = new T[];
    	
		if(isEmpty()){
			System.out.println("no record found");
			return;
		}
 
		Stack<BST<T>.BSTNode<T>> s = new Stack<BST<T>.BSTNode<T>>();
		
		BST<T>.BSTNode<T> current = root;
 
		while(!s.empty() || current!=null){
 
			if(current!=null)
			{
				s.push(current);
				current = current.left;
			}
			else
			{
				BST<T>.BSTNode<T> n = s.pop();
				System.out.println();
				String spaces = "";
				for (int i = 0; i < getDepth(n.getElement(), n.))
				System.out.println()
				currentNode=n.right;
			}
		}
	}
    
    
    

    /**
     * Internal method to remove a specified item from a subtree.
     *
     * @param x
     *            the item to remove.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     * @throws ItemNotFoundException
     *             if x is not found.
     */
    private Tuple<BSTNode<T>, T> remove(T x, BSTNode<T> node) {
        // This local variable will contain the new root of the subtree,
        // if the root needs to change.
        Tuple<BSTNode<T>, T> result = new Tuple<>(node, null);

        // If there's no more subtree to examine
        if (node == null) {
            throw new ItemNotFoundException();
        }

        Tuple<BSTNode<T>, T> temp = new Tuple<>(null, null);
        // if value should be to the left of the root
        if (x.compareTo(node.getElement()) < 0) {
            temp = remove(x, node.getLeft());
            result.setValue2(temp.getValue2());
            node.setLeft(temp.getValue1());
        }
        // if value should be to the right of the root
        else if (x.compareTo(node.getElement()) > 0) {
            temp = remove(x, node.getRight());
            result.setValue2(temp.getValue2());
            node.setRight(temp.getValue1());
        }
        // If value is on the current node
        else {
            result.setValue2(node.getElement());
            // If there are two children
            if (node.getLeft() != null && node.getRight() != null) {
                T elt = findMin(node.getRight()).getElement();
                if (elt.compareTo(x) == 0) {
                    remove(elt, node.getRight());
                }
                else {
                    remove(elt, node);
                }
                node.setElement(elt);
            }
            // If there is only one child on the left
            else if (node.getLeft() != null) {
                result.setValue1(node.getLeft());
            }
            // If there is only one child on the right
            else {
                result.setValue1(node.getRight());
            }
        }
        return result;
    }

    /**
     * Count the number of children of the node and determines the depth
     * 
     * @param value
     *            The value being searched for.
     * @param node
     *            The node being evaluated.
     * @param index
     *            The index of the element being searched for (repetition of the
     *            value)
     * @return The depth of the node
     */
    private int getDepth(T value, BSTNode<T> node, int index) {
        if (node == null) {
            return 0; // Not found
        }
        else if (value.compareTo(node.getElement()) < 0) {
            // Search in the left subtree
            int temp = getDepth(value, node.getLeft(), index);
            if (temp == 0) {
                return 0;
            }
            return temp + 1;
        }
        else if (value.compareTo(node.getElement()) > 0) {
            // Search in the right subtree
            int temp = getDepth(value, node.getRight(), index);
            if (temp == 0) {
                return 0;
            }
            return temp + 1;
        }
        else {
            if (index == 0) {
                return 1;
            }
            // Not this version of the element, explore right.
            index--;
            int temp = getDepth(value, node.getRight(), index);
            if (temp == 0) {
                return 0;
            }
            return temp + 1;
        }
    }

    /**
     * Internal method to find the smallest item in a subtree. precondition:
     * node != null
     * 
     * @param node
     *            the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BSTNode<T> findMin(BSTNode<T> node) {
        if (node.getLeft() != null) {
            return findMin(node.getLeft());
        }
        return node;
    }

    /**
     * Gets an in-order string representation of the tree
     * 
     * @return an in-order string representation of the tree
     */
    @Override
    public String toString() {
        if (root == null) {
            return "()";
        }
        else {
            return "(" + root.toString() + ")";
        }
    }

    private class BSTNode<E> {
        private E element;
        private BSTNode<E> left;
        private BSTNode<E> right;

        /**
         * Creates a node with no children.
         * 
         * @param theElement
         *            the element to store in this node.
         */
        BSTNode(E theElement) {
            element = theElement;
            left = null;
            right = null;
        }

        /**
         * Get the current data value stored in this node.
         * 
         * @return the element
         */
        public E getElement() {
            return element;
        }

        /**
         * Set the data value stored in this node.
         * 
         * @param value
         *            the new data value to set
         */
        public void setElement(E value) {
            element = value;
        }

        /**
         * Get the left child of this node.
         * 
         * @return a reference to the left child.
         */
        public BSTNode<E> getLeft() {
            return left;
        }

        /**
         * Set this node's left child.
         * 
         * @param value
         *            the node to point to as the left child.
         */
        public void setLeft(BSTNode<E> value) {
            left = value;
        }

        /**
         * Get the right child of this node.
         * 
         * @return a reference to the right child.
         */
        public BSTNode<E> getRight() {
            return right;
        }

        /**
         * Set this node's right child.
         * 
         * @param value
         *            the node to point to as the right child.
         */
        public void setRight(BSTNode<E> value) {
            right = value;
        }

        /**
         * Provides an in-order representation of the node
         * 
         * @return a string representation of the node
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (left != null) {
                builder.append(left.toString() + ", ");
            }
            builder.append(element.toString());
            if (right != null) {
                builder.append(", " + right.toString());
            }
            return builder.toString();
        }
    }

    /**
     * The iterator that traverses the tree and can return the values within
     * this BST.
     * 
     * @author Samuel Turner <samt5>
     * @version 2017.09.19
     */
    public class BSTIterator {
        private Stack stack;

        /**
         * Initializes the BSTIterator with a filled stack with the elements in
         * the BST.
         */
        public BSTIterator() {
            stack = new Stack();
            fillStack(root);
        }

        /**
         * Determines if there are more elements left to look through.
         * 
         * @return There are unexplored elements left over.
         */
        public boolean hasNext() {
            return stack.size > 0;
        }

        /**
         * Get the size of the stack.
         * 
         * @return total number of elements in the stack
         */
        public int getSize() {
            return stack.size;
        }

        /**
         * Returns the value of the next element in the tree. It then removes
         * that element from being looked at in the future.
         * 
         * @return The next available value.
         */
        public T next() {
            BSTNode<T> node = stack.pop();
            if (node == null) {
                return null;
            }
            return node.getElement();
        }

        /**
         * Looks at the next value but leaves it as the return of next().
         * 
         * @return The next available value.
         */
        public T getValue() {
            if (stack.peek() == null) {
                return null;
            }
            return stack.peek().getElement();
        }

        /**
         * Fills the stack with the tree rooted at the given BSTNode<T>. Will
         * always put the values in with the largest values on bottom and
         * smallest on top.
         * 
         * @param node
         *            The root node of the tree being evaluated.
         */
        private void fillStack(BSTNode<T> node) {
            if (node == null) {
                return;
            }
            if (node.getRight() != null) {
                fillStack(node.getRight());
            }
            stack.push(node);
            if (node.getLeft() != null) {
                fillStack(node.getLeft());
            }
        }

        private class Stack {
            /**
             * The top node of the stack.
             */
            public StackNode top;
            /**
             * The size of the stack.
             */
            public int size;

            /**
             * Constructor for this stack
             */
            public Stack() {
                top = null;
                size = 0;
            }

            /**
             * Put a node onto the stack.
             * 
             * @param node
             *            The node to be added
             */
            public void push(BSTNode<T> node) {
                StackNode newNode = new StackNode(node, null);
                if (top == null) {
                    top = newNode;
                }
                else {
                    newNode.previous = top;
                    top = newNode;
                }
                size++;
            }

            /**
             * Take the top node off the stack
             * 
             * @return The node removed
             */
            public BSTNode<T> pop() {
                if (top == null) {
                    return null;
                }
                StackNode oldTop = top;
                top = top.previous;
                size--;
                return oldTop.value;
            }

            /**
             * Look at the top node on the stack
             * 
             * @return The node on top of the stack
             */
            public BSTNode<T> peek() {
                if (top == null) {
                    return null;
                }
                return top.value;
            }
        }

        private class StackNode {
            protected BSTNode<T> value;
            protected StackNode previous;

            /**
             * The constructor for the stack node that initializes the node's
             * values.
             * 
             * @param data
             *            The data that will be placed in the node
             * @param node
             *            The node previous node in the stack.
             */
            public StackNode(BSTNode<T> data, StackNode node) {
                value = data;
                previous = node;
            }
        }
    }
}
