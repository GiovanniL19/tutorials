using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ScreenScraper
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void closeBtn_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        
        public void runCode(string url, object sender, EventArgs e)
        {
            if (url.Contains("http://"))
            {
                string sourceCode = Worker.getSourceCode(url);
                source.Text = sourceCode;
                StreamWriter sw = new StreamWriter("sourcecode.txt");
                sw.Write(sourceCode);
                sw.Close();

            }
            else
            {
                url = url.Insert(0, "http://");
                string sourceCode = Worker.getSourceCode(url);
                source.Text = sourceCode;
                loadingLabel.Visible = false;
                StreamWriter sw = new StreamWriter("sourcecode.txt");
                sw.Write(sourceCode);
                sw.Close();

            }
        }

        private void scrapeButton_Click(object sender, EventArgs e)
        {
            loadingLabel.Visible = true;
            string url = urlText.Text;
            runCode(url, sender, e);
        }

        private void urlText_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                loadingLabel.Visible = true;
                string url = urlText.Text;
                runCode(url, sender, e);
            }
        }

        private void parseBtn_Click(object sender, EventArgs e)
        {
            Worker.parseCode("sourcecode.txt");
        }
    }
}
