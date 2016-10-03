using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace ScreenScraper
{
    class Worker
    {
        public static string getSourceCode(string url)
        {
            //Define req, resp and sr
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
            HttpWebResponse resp = (HttpWebResponse)req.GetResponse();
            StreamReader sr = new StreamReader(resp.GetResponseStream());

            string sourceCode = sr.ReadToEnd();
            sr.Close();
            resp.Close();
            return sourceCode;
        }

        public static void parseCode(string pFName)
        {
            StreamReader sr = new StreamReader(pFName);
            string sourceCode = sr.ReadToEnd();
            sr.Close();

            ArrayList aL = new ArrayList();

            Regex r = new Regex(@"<a[\s]+[^>]*?href[\s]?=[\s\""\']+(?<href>.*?)[\""\']+.*?>(?<fileName>[^<]+|.*?)?<\/a>");

            MatchCollection mcl = r.Matches(sourceCode);

            foreach (Match ml in mcl)
            {
                foreach (Group g in ml.Groups)
                {
                    string b = g.Value + "";
                    aL.Add(b);
                }
            }

            StreamWriter sw = new StreamWriter("links.txt");
            foreach (string s in aL)
            sw.WriteLine(s);
            sw.Close();
        }
    }
}
