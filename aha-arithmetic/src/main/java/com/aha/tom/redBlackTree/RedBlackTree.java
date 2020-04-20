package com.aha.tom.redBlackTree;

public class RedBlackTree<T extends Comparable<T>> {

    private RBNode<T> rootNode;
    public static final boolean RED = false;
    public static final boolean BLAKE = true;



    public class RBNode<T extends Comparable<T>>{
        boolean color;
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> parent;
        T value;

        public RBNode(boolean color, RBNode<T> left, RBNode<T> right, RBNode<T> parent, T value) {
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.value = value;
        }
    }

    /**
     * 插入数据
     * @param key
     */
    public void insert(T key){
        RBNode<T> node = new RBNode<>(BLAKE,null,null,null,key);
        if(null != node){
            insert(node);
        }
    }





    public RedBlackTree(RBNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    public RedBlackTree() {
        this(null);
    }

    /**
     * 红黑树插入数据
     * @param node
     */
    public void insert(RBNode<T> node){
        //先获取根节点和一个查询为null节点的父节点的占位
        RBNode xNode = rootNode;
        RBNode yNode = null;
        //循环对比当前数据和以右节点，查找到该路径下最后一个非空节点
        while(xNode != null){
            //将当前的非空节点赋予Y   //注意--------
            yNode = xNode;
            int camp = node.value.compareTo((T) xNode.value);
            if(camp > 0){
                xNode = xNode.right;
            }else {
                xNode = xNode.left;
            }
        }

        //将循环获取到的最后一个节点，进行判断处理,先默认y为node的父节点
        node.parent = yNode;  //注意----------
        if(yNode != null){
            int camp = node.value.compareTo((T) yNode.value);
            if(camp > 0){
                yNode.right = node;
            }else {
                yNode.left = node;
            }

        }else {
            rootNode = node;
        }

        //设置刚插入的节点颜色为红色
        node.color = RED;
        //红黑树再平衡；
        insertFixUp(node);



    }

    /**
     * 红黑树再平衡
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * @param node
     */
    private void insertFixUp(RBNode<T> node) {
        //假设当前节点的父节点是红色的
        RBNode parent;
        RBNode gparent;

        //当前节点的父节点不为空，且父节点是红色
        while ((parent = parentOf(node)) != null && isRed(node)){
            //先获取祖父节点
            gparent = parentOf(parent);

            //先判断父节点是祖父节点的左节点还是右节点
            //如果父节点是左节点
            if(parent == gparent.left){

                RBNode<T> uncle = gparent.right;

                //如果叔父节点为红色
                if(uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    //循环修正树的颜色
                    node = gparent;
                    continue;
                }

                //如果节点位于父节点的右侧，叔叔是黑色，且当前节点是右孩子(两次旋转，先左后右)
                if(node == parent.right){
                    RBNode<T> temp;
                    leftTurn(parent);
                    //将父节点的索引执行自己，将node指向原父节点，即转换后的新关系。
                    temp = parent;
                    parent = node;
                    node = temp;

                }

                setBlack(parent);
                setRed(gparent);
                //进行右旋转
                rightTurn(gparent);
            }else{
                //如果叔父节点是红色
                RBNode<T> uncle = gparent.right;
                if(uncle != null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    node = gparent;
                    continue;
                }
                //如果接是在父节点左侧，先进行右旋

                if(node == parent.left){
                    RBNode<T> temp;
                    rightTurn(parent);
                    temp = parent;
                    parent = node;
                    node = temp;

                }
                //修改颜色，进行左旋

                setBlack(parent);
                setRed(gparent);
                leftTurn(gparent);
            }

        }
        //设置根节点为黑色
        setBlack(rootNode);

    }

    private boolean isRed(RBNode<T> node) {
        return (node != null && node.color == RED) ? true : false;
    }

    private RBNode parentOf(RBNode<T> node) {
        return  node != null ? node.parent : null;
    }
    //设置节点颜色
    public void setBlack(RBNode node) {
        if(node != null){
            node.color = BLAKE;
        }
    }

    public void setRed(RBNode node) {
        if(node != null){
            node.color = RED;
        }
    }

    public void leftTurn(RBNode<T> x){
        //左转的核心是，将原有的 x-->y关系转变为y--->的关系，并对节点数据转移。主要操作是先处理y节点的数据
        //在处理x节点和y的关系

        /**
         * 该段就是在处理y节点的数据转移到x节点
         */
        RBNode<T> y = x.right;
        x.right = y.left;
        if(y.left != null){
            //将y非空的子节点的父节点指向x
            y.left.parent = x;
        }
        /**
         * 此处是为例处理x的父节点转移给y
         *
         */
        y.parent = x.parent;
        if(x.parent == null){
            //将根节点设置为y
           this.rootNode = y;
        }else{
            //如果x是其父节点的左节点
            if(x.parent.left == x){
                x.parent.left = y;
            }else{
                x.parent.right = y;
            }
        }

        /**
         * 此处是建立x和y之间的关系
         */

        y.left = x;
        x.parent = y;
    }

    public void rightTurn(RBNode<T> x){
        RBNode<T> y = x.left;
        x.left = y.right;
        if(y.right != null){
            y.right.parent = x;
        }

        y.parent = x.parent;
        if(x.parent == null){
            this.rootNode = y;
        }else{
            if(x.parent.left == x){
                x.parent.left = y;

            }else {
                x.parent.right = y;
            }
        }


        y.right = x;
        x.parent = y;


    }


}