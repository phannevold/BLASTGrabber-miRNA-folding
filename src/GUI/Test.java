/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLASTGrabber.Facade;
import BLASTGrabber.Facade.BLASTGrabberHit;
import BLASTGrabber.Facade.BLASTGrabberQuery;
import BLASTGrabber.Facade.BLASTGrabberStatistic;
import Data.miRNAHit;
import Data.miRNAQuery;
import Plugin.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;

/**
 *
 * @author Petter
 */
public class Test extends javax.swing.JInternalFrame implements TreeSelectionListener{

    /**
     * Creates new form Test
     */
    
    private Main main;
    private Facade facade;
    private HashMap<String, BLASTGrabberQuery> queries;
    
    public Test(HashMap<String, BLASTGrabberQuery> queries, Facade facade) {
    
        this.main = Main.getMain();
        this.facade = facade;
        this.queries = queries;
        initComponents();
        initTree(convertQueries(queries));
        
        
    }
    
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        
        jTextArea1.setText(null);
        
       
        
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
        Object current = currentNode.getUserObject();
        
        
        if(current instanceof miRNAHit){
            miRNAHit hit = (miRNAHit) current;
            HashMap<String, BLASTGrabberQuery> bgQuery = new HashMap<String, BLASTGrabberQuery>();
            DefaultMutableTreeNode queryNode = (DefaultMutableTreeNode)currentNode.getParent();
            miRNAQuery query = (miRNAQuery)queryNode.getUserObject();
            ArrayList<String> sequence;
            
//            StringBuilder hitData = new StringBuilder();
//            for(BLASTGrabberStatistic i : hit.Statistics)
//                if(!i.Key.equals(""))
//                    hitData.append(i.Key + ":\t " + i.Name + ":\t " + i.Value + "\n");
//            jTextArea1.setText(hitData.toString());
            
            
//            bgQuery.put(query.Name, query);
            sequence = facade.getFASTACustomDBSequences(queries);
            if(sequence != null)
                for(String s: sequence)
                    jTextArea1.append(s + "/n");
            else
                jTextArea1.setText("No sequence data");
            
            
            
        }
        
        else
            jTextArea1.setText(null);
    }
    
    
    private void initTree(HashMap<String, miRNAQuery> queries){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode();
        
        Iterator<String> qIt = queries.keySet().iterator();
        String currentKey;
        miRNAQuery currentQuery;
        while(qIt.hasNext()){
            currentKey = qIt.next();
            currentQuery = queries.get(currentKey);
            DefaultMutableTreeNode currentQueryNode = new DefaultMutableTreeNode(currentQuery);
            top.add(currentQueryNode);
                        
            for(miRNAHit hit : currentQuery.miRNAHits){
                currentQueryNode.add(new DefaultMutableTreeNode(hit));
            }
        }
        
        TreeModel tm = new DefaultTreeModel(top);
        jTree1.setModel(tm);
        jTree1.addTreeSelectionListener(this);
    }
    
        private HashMap<String, miRNAQuery> convertQueries(HashMap<String, BLASTGrabberQuery> BGQueries){
        HashMap<String, miRNAQuery> miRNAQueries = new HashMap<String, miRNAQuery>();
        
        Iterator<String> queryIterator = BGQueries.keySet().iterator();
        
        BLASTGrabberQuery currentQuery;
        String currentKey;
        while(queryIterator.hasNext()){
            currentKey = queryIterator.next();
            miRNAQueries.put(currentKey, new miRNAQuery(BGQueries.get(currentKey)));
        }
        
        return miRNAQueries;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTree1.setRootVisible(false);
        jScrollPane3.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

   
}
