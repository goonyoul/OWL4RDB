import java.awt.event.*;
import java.io.Serializable;

import javax.swing.JTree;
import javax.swing.tree.*;

public class Node extends JTree implements Serializable{
	DefaultMutableTreeNode rootNode;
	JTree tree;
	
	public DefaultMutableTreeNode createNodes()
	{
      rootNode=new DefaultMutableTreeNode("Root");

	  return rootNode;
	}
	
	Node(){
		rootNode = createNodes();
		tree = new JTree(rootNode);
	}
	public void addNode(String st){
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(st);
		TreePath selectionPath = tree.getSelectionPath();
		if(selectionPath != null){
			DefaultMutableTreeNode selectedNode =(DefaultMutableTreeNode)selectionPath.getLastPathComponent();
			((DefaultTreeModel)tree.getModel()).insertNodeInto(newNode,selectedNode,selectedNode.getChildCount());
		}
	}
	public void removeNode(){
		TreePath selectionPath=tree.getSelectionPath();
		if(selectionPath != null){
			DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
			if(!selectedNode.equals(rootNode)){
				((DefaultTreeModel)tree.getModel()).removeNodeFromParent(selectedNode);
			}
		}
	}
	public void setNode(String st){
		TreePath selectionPath=tree.getSelectionPath();
		if(selectionPath != null){
			DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
			if(!selectedNode.equals(null))
				selectedNode.setUserObject(st);
		}
	}
	public String getNodeName(){
		TreePath selectionPath=tree.getSelectionPath();
		if(selectionPath!=null){
			DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
			if(!selectedNode.equals(null))
				return selectedNode.toString();
			else
				return null;
		}
		return null;
	}
	public String pack(){
		String string = "";
		DefaultMutableTreeNode leaf = rootNode.getFirstLeaf();
		do{
			TreeNode[] path = leaf.getPath();
			for(int i=0;i<path.length-1;i++){
				string += path[i];
				string += " ";
			}
			string += path[path.length-1];
			string += '\n';
		}while((leaf = leaf.getNextLeaf()) != null);
		
		return string;
	}
	public void unpack(String data){
		String[] string = data.split(" ");
		rootNode.setUserObject(string[0]);
		DefaultMutableTreeNode child = new DefaultMutableTreeNode();
		child=rootNode;
		for(int i =1; i<string.length;i++){
			DefaultMutableTreeNode newchild = new DefaultMutableTreeNode();
			newchild.setUserObject(string[i]);

			((DefaultTreeModel)tree.getModel()).insertNodeInto(newchild,child,child.getChildCount());
			child = newchild;
		}
	}
}