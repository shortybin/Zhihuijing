package com.bibi.wisdom.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Solution {


    public static void main(String[] args) {
        Solution testSuanfa = new Solution();

//        List<String> list=testSuanfa.letterCombinations("23");
//        System.out.println(list);

//        int[] arr = {1, 0, -1, 0, -2, 2};
//        List<List<Integer>> lists = testSuanfa.fourSum(arr, 0);



        ListNode head=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        ListNode node6=new ListNode(3);

        head.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
//        node5.next=node6;

//        System.out.println(testSuanfa.mergeTwoLists(head,null));
//        ListNode result=testSuanfa.reverseKGroup(head,3);
//        while (result!=null){
//            System.out.println(result.val);
//            result=result.next;
//        }


        int[] arr={1,5,1};


        testSuanfa.nextPermutation(arr);
//        System.out.println(testSuanfa.removeDuplicates(arr));
//        System.out.println(testSuanfa.removeElement(arr,3));
//        System.out.println(Arrays.toString(arr));
//
//        ListNode node=testSuanfa.removeNthFromEnd(head,2);
//
//        System.out.print(node+"----");
//        while (node.next!=null){
//            node=node.next;
//            System.out.print(node+"----");
//        }


//        System.out.println(testSuanfa.isValid("["));

//        System.out.println(testSuanfa.strStr("mississippi",
//                "issip"));

//        System.out.println(testSuanfa.divide(10,3));


    }


    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        String[][] simbols = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}, {"j", "k", "l"}, {"m", "n", "o"}, {"p", "q", "r", "s"}, {"t", "u", "v"}, {"w", "x", "y", "z"}};
        List<Character> listNumber = Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9');

        List<String[]> strings = new ArrayList<>();

        int index = -1;

        for (int i = 0; i < digits.length(); i++) {
            index = listNumber.indexOf(digits.charAt(i));
            if (index == -1) continue;

            String[] firstArr = simbols[index];
//            if(firstArr.length==4){
//                strings.add(0,firstArr);
//            }else
            strings.add(firstArr);
        }

        if (strings.size() == 0)
            return list;


        list = combination(strings, 0, list, "");


        return list;
    }


    private List<String> combination(List<String[]> strings, int i, List<String> list, String stemp) {

        if (i < strings.size() - 1) {
            for (int j = 0; j < strings.get(i).length; j++) {
                list = combination(strings, i + 1, list, stemp + strings.get(i)[j]);
            }
        } else {
            for (int j = 0; j < strings.get(i).length; j++) {
                list.add(stemp + strings.get(i)[j]);
            }
        }

        return list;
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {

        Set<List<Integer>> lists = new HashSet<>();
        if (nums.length < 4)
            return new ArrayList<>();


        long startTime = System.currentTimeMillis();

        Arrays.sort(nums);


        for (int i = 0; i < nums.length - 3; i++) {

            for (int j = i + 1; j < nums.length - 2; j++) {
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {

                    int sum = nums[i] + nums[j] + nums[start] + nums[end];

                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[start]);
                        list.add(nums[end]);
                        lists.add(list);
                    }

                    if (target - sum < 0)
                        end--;
                    else
                        start++;

                }
            }
        }

        long duration = System.currentTimeMillis() - startTime;

        return new ArrayList<>(lists);
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }


        @Override
        public String toString() {
            return val+"";
        }
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head.next==null&&n==1)
            return null;

        List<ListNode> list=new ArrayList<>();

        list.add(head);

        ListNode temp=head;

        while (temp.next!=null){
            temp=temp.next;
            list.add(temp);
        }


        int realIndex=list.size();
        while (n>0){
            realIndex--;
            n--;
        }


        if(head==list.get(realIndex)){
            return head.next;
        }

        temp=head;

        while (temp.next!=null){

            if(temp.next==list.get(realIndex)){
                temp.next=temp.next.next;
                break;
            }

            temp=temp.next;

        }



        return head;
    }


    public boolean isValid(String s) {
        if(s==null||s.isEmpty())
            return true;

        Stack<Character> stack1=new Stack<>();
        Queue<Character> queue=new LinkedList<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                stack1.push(c);
            }else {
                if(stack1.empty()) return false;
                char cc=stack1.pop();
                if(c==')'&&cc!='(') return false;
                if(c==']'&&cc!='[') return false;
                if(c=='}'&&cc!='{') return false;
            }
        }

//        if(stack1.size()!=queue.size())
//            return false;
//
//        while (!stack1.empty()){
//            char c=stack1.pop();
//            char c1=queue.poll();
//            switch (c){
//                case '(':
//                    if(c1!=')')
//                        return false;
//                    break;
//                case '[':
//                    if(c1!=']')
//                        return false;
//                    break;
//                case '{':
//                    if(c1!='}')
//                        return false;
//                    break;
//            }
//        }

        return stack1.empty();
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null&&l2==null)
            return null;

        List<ListNode> listNodes=new ArrayList<>();
        if(l1!=null)
            listNodes.add(l1);
        if(l2!=null)
            listNodes.add(l2);
        while (l1!=null&&l1.next!=null){
            l1=l1.next;
            listNodes.add(l1);
        }
        while (l2!=null&&l2.next!=null){
            l2=l2.next;
            listNodes.add(l2);
        }


        if(listNodes.size()==1)
            return listNodes.get(0);


        ListNode temp=null;

        int j=0;

        for (int i = 1; i < listNodes.size(); i++) {


            j=i;

            temp=listNodes.get(i);

            while (j>0&&temp.val<listNodes.get(j-1).val){
                listNodes.set(j,listNodes.get(j-1));
                j--;
            }


            if(j!=i){
                listNodes.set(j,temp);
            }
        }

//        System.out.println(listNodes);

        ListNode dummy=new ListNode(0);

        ListNode head=listNodes.get(0);

        dummy.next=head;

        for (int i = 1; i < listNodes.size(); i++) {
            ListNode node=listNodes.get(i);
            head.next=node;
            head=head.next;
        }

        return dummy.next;
    }


    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        ListNode dummy=new ListNode(-1);


        ListNode prev=dummy;

        while (l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                prev.next=l1;
                l1=l1.next;
            }else {
                prev.next=l2;
                l2=l2.next;
            }

            prev=prev.next;
        }

        prev.next=l1==null?l2:l1;

        return dummy;
    }








    public List<String> generateParenthesis(int n) {
        List<String> list=new ArrayList<>();
        generate(new char[2*n],0,list);

        return list;
    }



    private void generate(char[] chars,int pos,List<String> list){
        if(pos==chars.length){
            if(valid(chars))
                list.add(new String(chars));
        }else {
            chars[pos]='(';
            generate(chars,pos+1,list);
            chars[pos]=')';
            generate(chars,pos+1,list);
        }


    }

    private boolean valid(char[] chars){
        int balance=0;
        for(char c:chars){
            if(c=='(') balance++;
            else balance--;
            if(balance<0) return false;
        }

        return (balance==0);
    }



    private void backtrack(List<String> list,String cur,int open ,int close,int max){
        if(cur.length()==max*2)
            list.add(cur);

        if(open<max){
            backtrack(list,cur+"(",open+1,close,max);
        }

        if(close<max){
            backtrack(list,cur+")",open,close+1,max);
        }
    }


    public ListNode mergeTwoLists3(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=mergeTwoLists3(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeTwoLists3(l1,l2.next);
            return l2;
        }

    }


    public ListNode merge(ListNode[] lists,int left,int right){
        if(left==right) return lists[left];
        int mid=left+(right-left)/2;
        ListNode l1=merge(lists,left,mid);
        ListNode l2=merge(lists,mid+1,right);
        return mergeTwoLists3(l1,l2);
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0)return null;
        return merge(lists,0,lists.length-1);
    }


    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null) return null;
        Map<ListNode,ListNode> map=new LinkedHashMap<>();
        while(head!=null&&head.next!=null){
            map.put(head,head.next);
            head=head.next.next;
        }
        ListNode dummy=new ListNode(-1);
        ListNode temp=dummy;
        Set<ListNode> set=map.keySet();
        Iterator<ListNode> iterator=set.iterator();
        while(iterator.hasNext()){
            ListNode node=iterator.next();
            ListNode value=map.get(node);
            node.next=null;
            value.next=node;
            temp.next=value;
            temp=temp.next.next;
        }

        return dummy.next;

    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null)
            return head;
        Stack<ListNode> stack=new Stack<>();
        int index=0;
        ListNode temp=head;
        while (index<k&&temp!=null){
            stack.push(temp);
            temp=temp.next;
            index++;
        }
        if(stack.size()<k)
            return head;

        ListNode dummy=new ListNode(-1);

        dummy.next=head=stack.pop();

        ListNode next=head.next;

        while (!stack.isEmpty()){
            head.next=stack.pop();
            head=head.next;
        }
        head.next=reverseKGroup(next,k);

        return dummy.next;
    }


    public int removeDuplicates(int[] nums) {

        if(nums==null||nums.length==0)
            return 0;
        int j=0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[j]!=nums[i]){
                j++;
                nums[j]=nums[i];
            }
        }

        return j+1;

    }


    public int removeElement(int[] nums, int val) {
        if(nums==null||nums.length==0)
            return 0;
        int i=0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }


    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()){
            return 0;
        }
        if(haystack.isEmpty())
            return -1;
        if(needle.length()>haystack.length())
            return -1;
        int needleIndex=0;

        for(int i=0;i<haystack.length();i++){
            char c=haystack.charAt(i);
            char n=needle.charAt(needleIndex);
            if(c==n){
                if(needleIndex<needle.length()-1)
                    needleIndex++;
                else{
                    if(needleIndex==needle.length()-1){
                        return i-needleIndex;
                    }
                }
            }else{
                needleIndex=0;
            }
        }
        return -1;
    }


    public int divide(int dividend, int divisor) {
        if(dividend==0)
            return 0;
        long result=0;
        boolean hasMinus=false;
        if(dividend>0&&divisor>0){

        }else if(dividend<0&&divisor<0){
            // dividend=-dividend;
            // divisor=-divisor;

        }else if(dividend<0){
            // dividend=-dividend;
            hasMinus=true;

        }else{
            // divisor=-divisor;
            hasMinus=true;

        }

        long D1=Math.abs((long)dividend);
        long D2=Math.abs((long)divisor);
        int i=1;
        while(D1>=D2){
            long temp=D2;
            i=1;
            while (D1>=temp){
                D1-=temp;
                result+=i;
                i <<= 1;
                temp<<=1;
            }

        }



        if(hasMinus){
            result=-result;
        }

        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int)result;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list=new ArrayList<>();

        if(s==null||s.length()==0)
            return list;
        if(words==null||words.length==0)
            return list;

        int wordLength=words[0].length();

        int totalWordsLength=wordLength*words.length;

        Map<String,Integer> map=new HashMap<>();
        for(String word:words){
            map.put(word,map.getOrDefault(word,0)+1);
        }

        for (int i = 0; i < s.length() - totalWordsLength + 1; i++) {
            String str=s.substring(i,i+totalWordsLength);

            Map<String,Integer> temp=new HashMap<>();
            for (int j = 0; j < str.length(); j+=wordLength) {
                String s1=str.substring(j,j+wordLength);

                temp.put(s1,temp.getOrDefault(s1,0)+1);
            }

            if(map.equals(temp)) list.add(i);
        }


        return list;
    }

    public void nextPermutation(int[] nums) {

        int[] nums2= Arrays.copyOf(nums,nums.length);
        int j=0;
        for (int i = 1; i <nums2.length ; i++) {
            j=i;
            while (j>0&&nums2[j]>nums2[j-1]){
                int temp=nums2[j];
                nums2[j]=nums2[j-1];
                nums2[j-1]=temp;
                j--;
            }
        }

        boolean isSame=true;
        int x = 0;
        for (; x < nums.length; x++) {
            if(nums[x]!=nums2[x]){
                isSame=false;
            }
        }

        if(isSame){
            Arrays.sort(nums);
        }else {
            for (int i = nums.length-2; i>=0; i--) {
                int index=0;

                if(nums[i]<nums[i+1]){
                    index=nums.length-1;

                    while (index>=0&&nums[index]<=nums[i]){
                        index--;
                    }

                    int temp=nums[i];
                    nums[i]=nums[index];
                    nums[index]=temp;

                    int start=i+1;
                    index=nums.length-1;
                    while (start<index){
                        temp=nums[start];
                        nums[start]=nums[index];
                        nums[index]=temp;
                        start++;
                        index--;
                    }

                    break;
                }

            }
        }

//        Arrays.sort(nums2);



        System.out.println(Arrays.toString(nums));

    }


    public int search(int[] nums, int target) {

        return 0;

    }



}
