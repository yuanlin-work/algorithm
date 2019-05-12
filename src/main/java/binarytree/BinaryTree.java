package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree <T extends Comparable>{

    class TreeNode<T>{
        T t;
        TreeNode<T> left = null;
        TreeNode<T> right = null;
        TreeNode<T> parent = null;
		boolean isLeaf = true;
		int bf = 0;//平衡因子，=右侧子树高度-左侧子树高度
		public boolean isLeaf() {
			return isLeaf;
		}

		public TreeNode(T value){
            t = value;
        }
    }

    TreeNode<T> root;

    public int maxDepth(TreeNode node) {
        if(node == null)return 0;
        return Math.max(maxDepth(node.left),maxDepth(node.right))+1;
    }

	/**
	 * 添加平衡因子，在添加的时候计算
	 * @param t
	 */
	public void insert(T t){
        TreeNode<T> e = new TreeNode<>(t);
        if(root == null){
        	root = e;
        	return;
		}
        TreeNode current = root;
        TreeNode parent = null;
		Stack<TreeNode> stacks = new Stack<>();
        while (true){
            if(t.compareTo(current.t)<0){
				stacks.push(current);
                parent = current;
                current = parent.left;
                if(current == null){
                    parent.left = e;
					e.parent = parent;
					parent.isLeaf=false;
					balance(stacks);
                    break;
                }
            }else if(t.compareTo(current.t)>0){
				stacks.push(current);
               	parent = current;
               	current = parent.right;
               if(current == null){
                   parent.right = e;
				   e.parent = parent;
				   parent.isLeaf=false;
				   balance(stacks);
                   break;
               }
            }else {
                break;
            }
        }
    }
    void balance(Stack<TreeNode> stacks){
		if(stacks.empty())return;
		TreeNode treeNode;
		TreeNode unbalanceNode=null;
		//重新计算影响到的节点的平衡因子，并找出旋转节点
		while (!stacks.isEmpty()){
			treeNode=stacks.pop();
			treeNode.bf = maxDepth(treeNode.right)-maxDepth(treeNode.left);
			if(treeNode.bf<-1 || treeNode.bf > 1){
				unbalanceNode=treeNode;
				doBalance(unbalanceNode);
			}
		}
	}
	TreeNode doBalance(TreeNode unbalanceNode){
		unbalanceNode.bf=maxDepth(unbalanceNode.right)-maxDepth(unbalanceNode.left);
		if(unbalanceNode!=null){
			//该节点左孩子长，且它的左孩子也是左孩子长，或者左孩子平衡，进行一次右旋
			if(unbalanceNode.bf<-1 && unbalanceNode.left.bf<=0){
				return RotateR(unbalanceNode);
			}else if (unbalanceNode.bf<-1 && unbalanceNode.left.bf > 0){
				//该节点左孩子长，且它的左孩子是右孩子长，先将该节点的左孩子进行一次左旋，然后对该节点进行一次右旋
				return RotateLR(unbalanceNode);
			}else if(unbalanceNode.bf > 1 && unbalanceNode.right.bf >= 0){
				return RotateL(unbalanceNode);
			}else if(unbalanceNode.bf > 1 && unbalanceNode.right.bf < 0){
				return RotateRL(unbalanceNode);
			}
		}
		return unbalanceNode.parent;
	}

    /**
     * 前序遍历输出，先遍历当前节点，再遍历该节点的左右孩子，适合无序的快速遍历，如果仅仅是
	 * 进行快速遍历，还不如用链表
     */
    public void pre_structure_print(TreeNode  root){
        TreeNode current = root;
        if(current == null)return;
        System.out.println(String.format("data %s",current.t));
        pre_structure_print(current.left);
        pre_structure_print(current.right);

    }
    /**
     * 中序遍历输出，升序还是降序,适合做两个方法，先遍历左孩子，直到没有左孩子后再遍历该节点。或者
	 * 先遍历右孩子，没有右孩子后再遍历该节点。两者的区别是遍历结果为正序还是倒叙
     */
    public void inorder_structure_print(TreeNode  root){
        TreeNode current = root;
        if(current == null)return;
        inorder_structure_print(current.left);
        System.out.println(String.format("data %s",current.t));
        inorder_structure_print(current.right);

    }

    /**
     * 后序遍历输出，适合批量删除清空，先遍历左右孩子，最后遍历当前节点
     */
    public void post_structure_print(TreeNode  root){
        TreeNode current = root;
        if(current == null)return;
        post_structure_print(current.right);
        System.out.println(String.format("data %s",current.t));
        post_structure_print(current.left);


    }
    /*
    查找二叉树,中序查找
     */
    public TreeNode<T> findKey(T value) {
        TreeNode current = root;
        while (true){
            if(current == null)return null;
            int i = value.compareTo(current.t);
            if(i>0){
                current = current.right;
            }else if(i < 0){
                current = current.left;
            }else {
                break;
            }
        }
        if(current.t.equals(value)){//应该是当结果为null的时候才会走
            return current;
        }else {
            return null;
        }
    }
    /*
    删除节点
     */
    public TreeNode<T> delete(T t){
        TreeNode treeNode = findKey(t);
        if(treeNode == null)return null;
        TreeNode parent = delete(treeNode);
       	while ((parent = doBalance(parent))!=null){}
        //删除动作不会删除里面的孩子的、父节点的信息，但是这些信息已经失效,不应该作为继续寻找的依据
        return treeNode;
    }

    /**
     * 删除节点，改变树的结构
     * @param treeNode
	 * @return 返回可能受平衡因子影响的节点
     */
    private TreeNode delete(TreeNode treeNode) {
        /*
        理最复杂的情况，该节点同时包含左孩子和右孩子，可以通过查找该节点的后继节点来替换该节点，同时把
        后继节点删除，也可以找前驱节点来替换该节点
        前驱节点：该节点左侧子树中最大的节点，前驱节点肯定是不包含右孩子节点的
        后继节点：该节点右侧子树中最小节点，该节点不会有左侧孩子
         */
        if(treeNode.left != null && treeNode.right != null){
            //采用前驱替换法
            TreeNode left_max = maxNode(treeNode.left);
            TreeNode parent = left_max.parent;
            /*//该节点不会有右孩子，但是可能有左孩子，将左孩子替换为该节点,此时还需要判断下该节点是个左孩子还是右
            //孩子，来决定该节点的左孩子是连接到父节点的左还是右
            if(left_max.left != null){
                if (left_max.parent.left == left_max){
                    left_max.parent.left = left_max.left;
                }else {
                    left_max.parent.right = left_max.left;
                }
            }*/
            //该节点不会有右孩子，而且该节点肯定是父节点的右孩子，所以直接将该节点的左孩子链接到它的父节点的右边
			parent.right = left_max.left;
			if(parent.left==null && parent.right==null){
				parent.isLeaf=true;
			}
            //得到了该前驱节点，替换掉要删除的节点，为了不改变该删除节点中的信息，采用连接的方式
            //先把该前驱节点连接到删除节点的父节点上去
            if(treeNode.parent != null){
                if(treeNode.parent.left == treeNode){
                    treeNode.parent.left = left_max;
                }else {
                    treeNode.parent.right = left_max;
                }

            }else {
                root = left_max;
            }
			//向上的父节点引用不能少
			left_max.parent = treeNode.parent;
            left_max.left = treeNode.left;
			treeNode.left.parent=left_max;
            left_max.right = treeNode.right;
            treeNode.right.parent=left_max;
			left_max.isLeaf = false;//无论它是不是叶子节点，现在肯定不是了
            //此时treeNode已经是一个游离节点了，即不会有节点连接他，但是他还会保存原来的链接信息，比如孩子节点
            //、父节点信息，需要注意的是，返回该节点继续使用后应该置为null，方便该节点信息垃圾回收
            return parent;
        }
        /*
        删除root节点的情况,因为已经排除了左右子树都存在的情况，这个时候只可能存在左或右一个子树,且不会对平衡因子产生影响
         */
        if(treeNode.parent == null ){
            if(treeNode.left != null){
                root = treeNode.left;
            }else {
                root = treeNode.right;
            }
           return treeNode.parent;
        }
        //此时删除节点只会有左孩子或者右孩子了
        if(treeNode.left != null){
            //左孩子不是null
            if(treeNode.parent.left == treeNode){
                treeNode.parent.left = treeNode.left;
            }else {
                treeNode.parent.right = treeNode.left;
            }
            treeNode.left.parent = treeNode.parent;
        } else if(treeNode.right != null){
            if(treeNode.parent.left == treeNode){
                treeNode.parent.left = treeNode.right;
            }else {
                treeNode.parent.right = treeNode.right;
            }
            treeNode.right.parent = treeNode.parent;
        }else {
            if(treeNode.parent.left == treeNode){
                treeNode.parent.left = null;
            }else {
                treeNode.parent.right = null;
            }
        }
		return treeNode.parent;
    }
    /*
    查找子树中最大的节点
     */
    private TreeNode<T> maxNode(TreeNode<T> treeNode) {
        if (treeNode == null){
            return null;
        }
        while (treeNode.right != null)
            treeNode = treeNode.right;
        return treeNode;
    }
    /*
    查找最小节点
     */
    private TreeNode<T> minNode(TreeNode<T> tTreeNode){
        if(tTreeNode == null)return null;
        while (tTreeNode.left != null){
            tTreeNode = tTreeNode.left;
        }
        return tTreeNode;
    }

    /*
    层次遍历，属于相同深度的元素顺序保存在列表中
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        func(lists,0,root);
        //将lists倒序
        for (int i = 0, j = lists.size() - 1; i < j; i++, j--) {
            List<Integer> temp = lists.get(i);
            lists.set(i, lists.get(j));
            lists.set(j, temp);
        }
        return lists;
    }
    public void func(List<List<Integer>>lists,int level,TreeNode treeNode){
        if(treeNode == null)return;
        if(lists.size() == level){//当层深和lists长度相等时，当前层的list还没创建，因为层高从0开始
            List<Integer> list = new ArrayList<>();
            list.add((Integer) treeNode.t);
            lists.add(list);
        }else { //当前层的list已经创建了，直接存储
            lists.get(level).add((Integer) treeNode.t);
        }
        func(lists,level + 1,treeNode.left);//先遍历所有是左孩子的节点，保证顺序
        func(lists,level + 1,treeNode.right);
    }
	/**
	 * 二叉树左单旋,逆时针
	 * param:treeNode 要旋转的节点
	 * 此节点变成自己的右孩子的左孩子，因为没有了右孩子，就拿右节点的左孩子当做自己的右孩子，如果存在的话
	 */
	TreeNode RotateL(TreeNode treeNode){
		TreeNode parent = treeNode.parent;
		TreeNode right = treeNode.right;
		//如果当前节点没有右孩子，左旋失败
		if(right == null)return null;
		TreeNode rightleft = right.left;
		right.left = treeNode;
		right.parent = parent;
		treeNode.parent = right;
		treeNode.right = rightleft;
		if(rightleft != null){
			rightleft.parent = treeNode;
		}
		if(parent == null){
			root = right;
		}
		if(parent !=null && parent.left == treeNode){
			parent.left = right;
		}else if(parent !=null && parent.right == treeNode){
			parent.right = right;
		}
		/**
		 * 左旋对平衡因子的影响，只有当前节点和它的右孩子的平衡因子产生影响，所以重新计算
		 */
		treeNode.bf = maxDepth(treeNode.right)-maxDepth(treeNode.left);
		right.bf = maxDepth(right.right)-maxDepth(right.left);
		return right;
	}


	/**
	 * 二叉树右单旋，顺时针
	 * @return 返回新的顶级节点
	 */
	TreeNode RotateR(TreeNode treeNode){
		TreeNode parent = treeNode.parent;
		TreeNode left = treeNode.left;
		//如果当前节点没有左孩子，右旋失败
		if(left == null)return null;
		TreeNode leftright = left.right;
		left.parent = parent;
		left.right = treeNode;
		treeNode.parent = left;
		treeNode.left = leftright;
		if(leftright!=null){
			leftright.parent=treeNode;
		}
		if(parent == null){
			root = left;
		}
		if(parent !=null && parent.left == treeNode){
			parent.left = left;
		}else if(parent !=null && parent.right == treeNode){
			parent.right = left;
		}
		treeNode.bf = maxDepth(treeNode.right)-maxDepth(treeNode.left);
		left.bf = maxDepth(left.right)-maxDepth(left.left);
		return left;
	}

	/**
	 * 二叉树左右双旋,适用于右侧过重，但是右侧最深节点位于右孩子的左侧的情况，先将过长节点移动到右孩子的右侧，
	 * 防止旋转的时候将过长的节点替换到左侧，造成新的不平衡
	 */
	TreeNode RotateLR(TreeNode treeNode){
		RotateL(treeNode.left);//左旋该节点，然后自己的位置现在是自己的parent
		return RotateR(treeNode);
	}
	/**
	 * 二叉树右左双旋
	 */
	TreeNode RotateRL(TreeNode treeNode){
		RotateR(treeNode.right);
		return RotateL(treeNode);
	}


	/**
	 * 采用前序打印二叉树
	 */
	void showValueInWindows(MyFrame myFrame){

		value_func(this.root,myFrame,0,0,null);
	}
	public void value_func(TreeNode treeNode, MyFrame myFrame, int x, int y , MyFrame.Point parent){
		TreeNode current = treeNode;
		if(current == null)return;
		MyFrame.Point _point = myFrame.addPoints(current.t,x,y,parent);
		value_func(current.left,myFrame,x-maxRightScale(current.left,1,1),y+1,_point);
		value_func(current.right,myFrame,x+maxLeftScale(current.right,1,1),y+1,_point);
	}

	/**
	 * 采用前序打印二叉树
	 */
	void showParentInWindows(MyFrame myFrame){

		parentValuefunc(this.root,myFrame,0,0,null);
	}
	public void parentValuefunc(TreeNode treeNode, MyFrame myFrame, int x, int y , MyFrame.Point parent){
		TreeNode current = treeNode;
		if(current == null)return;
		MyFrame.Point _point = myFrame.addPoints(current.parent==null?-1:current.parent.t,x,y,parent);
		parentValuefunc(current.left,myFrame,x-maxRightScale(current.left,1,1),y+1,_point);
		parentValuefunc(current.right,myFrame,x+maxLeftScale(current.right,1,1),y+1,_point);
	}

	void showBFInWindows(MyFrame myFrame){

		bfValuefunc(this.root,myFrame,0,0,null);
	}
	public void bfValuefunc(TreeNode treeNode, MyFrame myFrame, int x, int y , MyFrame.Point parent){
		TreeNode current = treeNode;
		if(current == null)return;
		MyFrame.Point _point = myFrame.addPoints(current.bf,x,y,parent);
		bfValuefunc(current.left,myFrame,x-maxRightScale(current.left,1,1),y+1,_point);
		bfValuefunc(current.right,myFrame,x+maxLeftScale(current.right,1,1),y+1,_point);
	}

	/**
	 * 这个节点的子树最靠右的偏移量,采用前序遍历
	 * @return
	 */
	int maxRightScale(TreeNode treeNode,int scale,int i){
		if(treeNode == null)return scale;
		if(!treeNode.isLeaf()){
			scale +=i;
		}
		scale = maxRightScale(treeNode.right,scale,1);
		scale = maxRightScale(treeNode.left,scale,-1);
		return scale+1;
	}
	int maxLeftScale(TreeNode treeNode,int scale,int i){
		if(treeNode == null)return scale;
		if(!treeNode.isLeaf()){
			scale +=i;
		}
		scale = maxRightScale(treeNode.left,scale,1);
		scale = maxRightScale(treeNode.right,scale,-1);
		return scale+1;
	}
}

