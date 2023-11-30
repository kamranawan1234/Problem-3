import java.util.Iterator;
import java.util.Stack;

/**
 * @author Kamran Awan
 * This class is used for creating the binary tree by accessing the root node.
 */
public class BinaryTree<E>
{
    private Node<E> root;

    /**
     * Sets the root to null.
     */
    BinaryTree()
    {
        root = null;
    }

    /**
     * @return root
     */
    public Node<E> getRoot()
    {
        return root;
    }

    /**
     * Sets the root.
     * @param root the root in the binary tree
     */
    public void setRoot(Node<E> root)
    {
        this.root = root;
    }

    /**
     * Iterator class that extends the Binary Tree class and implements the Iterator.
     */
    private class IteratorClass extends BinaryTree<E> implements Iterator
    {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> current;
        E lastItemReturned;

        /**
         * Constructs the iterator class, sets the current variable and pushes the nodes of the tree into the stack.
         */
        public IteratorClass()
        {
            this.current = root;
            while(current.getLeft() != null)
            {
                stack.push(current);
                current = current.getLeft();
            }
        }

        /**
         * Checks if the stack is empty.
         * @return true if the stack is not empty, false otherwise
         */
        @Override
        public boolean hasNext()
        {
            return !stack.isEmpty();
        }

        /**
         * Gets the next node in the tree and returns it.
         * @return next node
         */
        @Override
        public Object next()
        {
            lastItemReturned = current.getKey();
            if (current.getRight() != null)
            {
                stack.push(current);
                current = current.getRight();

                while (current.getLeft() != null)
                {
                    stack.push(current);
                    current = current.getLeft();
                }
            }
            else
            {
                while (!stack.isEmpty() && (stack.peek().equals(current)))
                {
                    current = stack.pop();
                }

                if (stack.isEmpty())
                {
                    current = null;
                }
                else
                {
                    current = stack.pop();
                }
            }
            return lastItemReturned;
        }

        /**
         * Throws an UnsupportedOperationException.
         */
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}