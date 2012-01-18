/*
 * frmClipboard.java
 */
package miRNAFolding;

import BLASTGrabber.Facade.BLASTGrabberHit;
import BLASTGrabber.Facade.BLASTGrabberQuery;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.net.URI;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 * @author Eirik Krogstad
 * @author Petter Hannevold
 */
public class FrmClipboard extends javax.swing.JInternalFrame implements ActionListener {
    private HashMap<String, BLASTGrabberQuery> queries;
    private FrmOptions frmOptions;

    /** Creates new form FrmClipboard */
    public FrmClipboard() {
        initComponents();
    }

    public void init(HashMap<String, BLASTGrabberQuery> queries) {
        this.queries = queries;

        DefaultListModel listModel = new DefaultListModel();
        jListQueries.setModel(listModel);

        int pos = 0;
        Iterator<String> itQ = queries.keySet().iterator();

        while (itQ.hasNext()) {
            String queryName = itQ.next();
            pos = listModel.getSize();
            listModel.add(pos, queryName + ":");
            Iterator<BLASTGrabberHit> itH = queries.get(queryName).Hits.iterator();
            while (itH.hasNext()) {
                pos = listModel.getSize();
                listModel.add(pos, "  " + itH.next().SequenceHeader);
            }
        }

        frmOptions = new FrmOptions();
        jButtonOptions.addActionListener(this);

        // to be removed - for testing purposes
        String test = ">test\nGGGCUAUUAGCUCAGUUGGUUAGAGCGCACCCCUGAUAAGGGUGAGGUCGCUGAUUCGAAUUCAGCAUAGCCCA";
        RNAFolder.runSequencePlot(test, frmOptions);
        loadSVG("test_ss.svg");
    }

    private void loadSVG(String filename) {
        try {
          URI uri = new File(filename).toURI();
          svgPanel.setSvgURI(uri);
        } catch (Exception e) {
          System.out.println(e.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int frmX = this.getX();
        int frmW = this.getWidth();
        int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
        if (frmX + frmW + frmOptions.getWidth() > screenW)
            frmOptions.setLocation(50, 50);
        else
            frmOptions.setLocation(frmX + frmW, this.getY());
    }

    // to be removed - for testing purposes
    public static void main(String[] args) {
        JFrame main = new JFrame("Test frame");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrmClipboard intFrm = new FrmClipboard();

        BLASTGrabberHit testHit1 = new BLASTGrabberHit();
        testHit1.SequenceHeader = ">test Hit one-1 (EINs) uno-en [singular mono alpha]";
        BLASTGrabberHit testHit2 = new BLASTGrabberHit();
        testHit2.SequenceHeader = ">test Hit two22 duo-bi (ZWEI-too to2) II [dual plural beta no. 2]";

        BLASTGrabberQuery testQuery = new BLASTGrabberQuery();
        testQuery.Name = "Test query";
        testQuery.Hits.add(testHit1);
        testQuery.Hits.add(testHit2);

        HashMap<String, BLASTGrabberQuery> testData = new HashMap<String, BLASTGrabberQuery>();
        testData.put(">test category 1 [RANDOM garb] age-2 make [THIS seemlike] actualdata", testQuery);

        intFrm.init(testData);
        main.add(intFrm);
        intFrm.setVisible(true);
        main.setVisible(true);
        main.pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        svgPanel = new com.kitfox.svg.app.beans.SVGPanel();
        jScrollPaneQueries = new javax.swing.JScrollPane();
        jListQueries = new javax.swing.JList();
        jPanelResults = new javax.swing.JPanel();
        jButtonOptions = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        svgPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));
        svgPanel.setFont(new java.awt.Font("SansSerif", 0, 11));
        svgPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        svgPanel.setScaleToFit(true);
        svgPanel.setUseAntiAlias(true);

        javax.swing.GroupLayout svgPanelLayout = new javax.swing.GroupLayout(svgPanel);
        svgPanel.setLayout(svgPanelLayout);
        svgPanelLayout.setHorizontalGroup(
            svgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        svgPanelLayout.setVerticalGroup(
            svgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );

        jListQueries.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneQueries.setViewportView(jListQueries);

        javax.swing.GroupLayout jPanelResultsLayout = new javax.swing.GroupLayout(jPanelResults);
        jPanelResults.setLayout(jPanelResultsLayout);
        jPanelResultsLayout.setHorizontalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        jPanelResultsLayout.setVerticalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        jButtonOptions.setText("Options...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneQueries, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(svgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOptions)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneQueries, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(svgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                    .addComponent(jButtonOptions))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOptions;
    private javax.swing.JList jListQueries;
    private javax.swing.JPanel jPanelResults;
    private javax.swing.JScrollPane jScrollPaneQueries;
    private com.kitfox.svg.app.beans.SVGPanel svgPanel;
    // End of variables declaration//GEN-END:variables
}