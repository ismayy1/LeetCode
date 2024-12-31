package madium;

/*
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order, and each of their nodes contains a single digit.
    Add the two numbers and return the sum as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // Deserialize method to convert a string to a linked list
    public static ListNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("[]")) {
            return null;
        }

        data = data.replace("[", "").replace("]", "")
                .replace(" ", "");
        String[] values = data.split(",");
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        for (String value : values) {
            current.next = new ListNode(Integer.parseInt(value));
            current = current.next;
        }

        return dummyHead.next;
    }

    // Serialize method to convert a linked list to a string
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode current = this;

        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}

public class Exc02_LinkedListAddition {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // Dummy node to simplify edge cases
        ListNode current = dummyHead;
        int carry = 0;

        // Iterate through both linked lists
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            // Add values from l1 and l2 if they exist
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Calculate new carry and current digit
            carry = sum / 10;
            int digit = sum % 10;

            // Add the digit as a new node
            current.next = new ListNode(digit);
            current = current.next;
        }

        return dummyHead.next; // Skip the dummy node and return the actual result
    }

    public static void main(String[] args) {
        // Deserialize input numbers
        ListNode l1 = ListNode.deserialize("[2,4,3]");
        ListNode l2 = ListNode.deserialize("[5,6,4]");

        Exc02_LinkedListAddition solution = new Exc02_LinkedListAddition();
        ListNode result = solution.addTwoNumbers(l1, l2);

        // Print the result: 807 (represented as 7 -> 0 -> 8)
        System.out.println("Result: " + result.serialize());
    }
}
