package binarytree;

import java.util.Random;

public class BinaryTreeDemo {
    public static void main(String[] args) {
		MyFrame myFrame = new MyFrame("test");// 创建实例,初始化界面

		Random random = new Random(1);
		BinaryTree binaryTree = new BinaryTree<Integer>();
		for (int i = 0; i < 28; i++) {
			Integer ii = random.nextInt(50);
			System.out.println(ii);
			binaryTree.insert(ii);
			binaryTree.showValueInWindows(myFrame);
//			myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),false,ii);
//			binaryTree.showBFInWindows(myFrame);
//			myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),false,ii);
//			binaryTree.showParentInWindows(myFrame);
			myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),true,ii);
		}
		/*binaryTree.delete(28);
		binaryTree.showValueInWindows(myFrame);
		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),false,"remove:"+28);
		binaryTree.showParentInWindows(myFrame);
		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),false,"remove:"+28);
		binaryTree.showBFInWindows(myFrame);
		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),true,"remove:"+28);

		binaryTree.delete(19);
		binaryTree.showValueInWindows(myFrame);
		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),false,"remove:"+19);
		binaryTree.showParentInWindows(myFrame);
		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),false,"remove:"+19);
		binaryTree.showBFInWindows(myFrame);
		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root),true,"remove:"+19);*/

//		binaryTree.showValueInWindows(myFrame);
//		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root));
//		binaryTree.showBFInWindows(myFrame);


//		binaryTree.showValueInWindows(myFrame);
//		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root));
//		binaryTree.showParentInWindows(myFrame);

//		myFrame.setHorize(binaryTree.maxDepth(binaryTree.root));
//		BinaryTree.TreeNode middle = binaryTree.RotateR(binaryTree.root);
//		binaryTree.root = middle;
//		binaryTree.showInWindows(myFrame);

		myFrame.draw();
    }

}
