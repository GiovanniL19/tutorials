namespace ScreenScraper
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panel1 = new System.Windows.Forms.Panel();
            this.parseBtn = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.urlText = new System.Windows.Forms.TextBox();
            this.scrapeButton = new System.Windows.Forms.Button();
            this.panel2 = new System.Windows.Forms.Panel();
            this.source = new System.Windows.Forms.Label();
            this.loadingLabel = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(66)))), ((int)(((byte)(121)))), ((int)(((byte)(148)))));
            this.panel1.Controls.Add(this.parseBtn);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Controls.Add(this.urlText);
            this.panel1.Controls.Add(this.scrapeButton);
            this.panel1.ForeColor = System.Drawing.Color.White;
            this.panel1.Location = new System.Drawing.Point(0, -1);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(809, 123);
            this.panel1.TabIndex = 1;
            // 
            // parseBtn
            // 
            this.parseBtn.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.parseBtn.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(128)))), ((int)(((byte)(0)))));
            this.parseBtn.FlatAppearance.BorderSize = 0;
            this.parseBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.parseBtn.Font = new System.Drawing.Font("Adobe Fan Heiti Std B", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.parseBtn.ForeColor = System.Drawing.Color.White;
            this.parseBtn.Location = new System.Drawing.Point(8, 83);
            this.parseBtn.Name = "parseBtn";
            this.parseBtn.Size = new System.Drawing.Size(113, 31);
            this.parseBtn.TabIndex = 5;
            this.parseBtn.Text = "Parse Source Code";
            this.parseBtn.UseVisualStyleBackColor = false;
            this.parseBtn.Click += new System.EventHandler(this.parseBtn_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(9, 23);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(76, 14);
            this.label1.TabIndex = 3;
            this.label1.Text = "URL to Scrape";
            // 
            // urlText
            // 
            this.urlText.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.urlText.BackColor = System.Drawing.Color.White;
            this.urlText.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.urlText.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F);
            this.urlText.ForeColor = System.Drawing.Color.DimGray;
            this.urlText.Location = new System.Drawing.Point(8, 40);
            this.urlText.Name = "urlText";
            this.urlText.Size = new System.Drawing.Size(731, 31);
            this.urlText.TabIndex = 2;
            this.urlText.KeyDown += new System.Windows.Forms.KeyEventHandler(this.urlText_KeyDown);
            // 
            // scrapeButton
            // 
            this.scrapeButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.scrapeButton.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(192)))), ((int)(((byte)(0)))));
            this.scrapeButton.FlatAppearance.BorderSize = 0;
            this.scrapeButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.scrapeButton.Font = new System.Drawing.Font("Adobe Fan Heiti Std B", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.scrapeButton.ForeColor = System.Drawing.Color.White;
            this.scrapeButton.Location = new System.Drawing.Point(736, 40);
            this.scrapeButton.Name = "scrapeButton";
            this.scrapeButton.Size = new System.Drawing.Size(56, 31);
            this.scrapeButton.TabIndex = 4;
            this.scrapeButton.Text = "Scrape";
            this.scrapeButton.UseVisualStyleBackColor = false;
            this.scrapeButton.Click += new System.EventHandler(this.scrapeButton_Click);
            // 
            // panel2
            // 
            this.panel2.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panel2.AutoScroll = true;
            this.panel2.BackColor = System.Drawing.Color.White;
            this.panel2.Controls.Add(this.source);
            this.panel2.Location = new System.Drawing.Point(12, 142);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(777, 394);
            this.panel2.TabIndex = 5;
            // 
            // source
            // 
            this.source.AutoSize = true;
            this.source.Location = new System.Drawing.Point(4, 4);
            this.source.Name = "source";
            this.source.Size = new System.Drawing.Size(0, 14);
            this.source.TabIndex = 0;
            // 
            // loadingLabel
            // 
            this.loadingLabel.AutoSize = true;
            this.loadingLabel.Location = new System.Drawing.Point(12, 125);
            this.loadingLabel.Name = "loadingLabel";
            this.loadingLabel.Size = new System.Drawing.Size(58, 14);
            this.loadingLabel.TabIndex = 6;
            this.loadingLabel.Text = "Loading...";
            this.loadingLabel.Visible = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 14F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(808, 549);
            this.Controls.Add(this.loadingLabel);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.panel1);
            this.Font = new System.Drawing.Font("Adobe Fan Heiti Std B", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ForeColor = System.Drawing.Color.DimGray;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Form1";
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TextBox urlText;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button scrapeButton;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Label source;
        private System.Windows.Forms.Label loadingLabel;
        private System.Windows.Forms.Button parseBtn;
    }
}

