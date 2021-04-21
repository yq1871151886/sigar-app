package com.tiamaes.cloud.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/29 16:55
 */
public class Test {

    public static void main(String[] args) {
        String s = "TM1610776585283TMQaPtZH8a\n" +
                "TM1604277414275TM3DJs8d5O\n" +
                "TM1595554532518TMjxH4L0JC\n" +
                "TM1610250636805TMEDwj5cx4\n" +
                "TM1610755451602TMkI1d6X57\n" +
                "TM1610777313675TMfeRqgOEU\n" +
                "TM1605006126843TMxWBd78ZI\n" +
                "TM1603510695217TMMVZUOdeF\n" +
                "TM1607072893265TMmFpw6omb\n" +
                "TM1610718214001TM3byDQxNR\n" +
                "TM1605225430523TMeP5ZLFif\n" +
                "TM1610718434346TMZbSHNLP0\n" +
                "TM1596099931280TMT4FRpSQN\n" +
                "TM1610782443254TMkr5ZQnIV\n" +
                "TM1610717833041TMl4Tsofvy\n" +
                "TM1595647872377TMRqalqgHn\n" +
                "TM1610755251275TMOc0dhilI\n" +
                "TM1601686090830TMhPV079xH\n" +
                "TM1610714615888TMqqAoBJ8M\n" +
                "TM1607641284167TMVLl8yaCV\n" +
                "TM1608111560316TM006rvetp\n" +
                "TM1601686090830TMhPV079xH\n" +
                "TM1595589308948TMRqalqgHn\n" +
                "TM1610772760368TMY1Tghicd\n" +
                "TM1608684419298TMKsuoqawP\n" +
                "TM1610721977529TMBVtUGaHq\n" +
                "TM1610709593620TMAH6Wpc0z\n" +
                "TM1607922849670TMCsoZSW3A\n" +
                "TM1610710005116TMN3HJzHab\n" +
                "TM1595835349488TMRqalqgHn\n" +
                "TM1604890796409TMewpptuTq\n" +
                "TM1605002320786TMfU3HvdYI\n" +
                "TM1610750159375TMar7EJmC3\n" +
                "TM1610270318997TMfDSciYEC\n" +
                "TM1595553902226TM16PmFJAi\n" +
                "TM1602719667781TMj8LJQz96\n" +
                "TM1604277771354TMSEDOSypC\n" +
                "TM1595554532518TMjxH4L0JC\n" +
                "TM1610012492206TMaFCyLPwE\n" +
                "TM1604967589237TMTDxhyRld\n" +
                "TM1605609571694TMXGwnTcT5\n" +
                "TM1605421242386TMAQnw4PVI\n" +
                "TM1608522703986TMSTiy5m7z\n" +
                "TM1595589308948TMRqalqgHn\n" +
                "TM1604810219945TMx10pvV1k\n" +
                "TM1604837893365TMkL1wFyvd\n" +
                "TM1595835349488TMRqalqgHn\n" +
                "TM1603349146215TMuRCNtEM8\n" +
                "TM1610773640744TMTSUY3XF7\n" +
                "TM1610320833380TMu8ajPEtd\n" +
                "TM1610712201776TM7bZqv7ad\n" +
                "TM1610762647861TMM7JU5BPk\n" +
                "TM1610715008070TMl4VQexuZ\n" +
                "TM1610774162370TMmrQqQ13Y\n" +
                "TM1607507512572TMTUGXDuVK\n" +
                "TM1610716186682TMGbCsPdDA\n" +
                "TM1606006316036TMW3hXYWA9\n" +
                "TM1608617856248TME0Os8UZ4\n" +
                "TM1610774332671TMOwSTjMGf\n" +
                "TM1610756645145TMpdf23p4w\n" +
                "TM1603949055441TM7B4WXEPt\n" +
                "TM1604277414275TM3DJs8d5O\n" +
                "TM1610777956886TMuqYoOhVS\n" +
                "TM1595656835734TMRqalqgHn\n" +
                "TM1606639952985TMGnYylE9A\n" +
                "TM1606951958784TMOsB51fIN\n" +
                "TM1609148786124TMk6TC5oat\n" +
                "TM1610711419010TM3eya7nGw\n" +
                "TM1595584547982TMRqalqgHn\n" +
                "TM1610778017513TMFSQdJ1uh\n" +
                "TM1603949055441TM7B4WXEPt\n" +
                "TM1602315194632TMj8LJQz96\n" +
                "TM1604810219945TMx10pvV1k\n" +
                "TM1610434351146TMk2e4tFDb\n" +
                "TM1595553902226TM16PmFJAi\n" +
                "TM1610710290540TMO5UJMKAZ\n" +
                "TM1602719667781TMj8LJQz96\n" +
                "TM1610748998601TMMxzjchFQ\n" +
                "TM1595584547982TMRqalqgHn\n" +
                "TM1610715158439TMqW7D0ztr\n" +
                "TM1610788308614TM2A5CbX2A\n" +
                "TM1609486125708TMT4xYVCnj\n" +
                "TM1610776735608TMV0AfAOOx\n" +
                "TM1595656835734TMRqalqgHn\n" +
                "TM1602891396316TMj8LJQz96\n" +
                "TM1611806490501TMaRrs3Iuk\n" +
                "TM1610410553120TMEoCgPe11\n" +
                "TM1610774563008TMOZqxKEAF\n" +
                "TM1610170028726TM4tKBOG0X\n" +
                "TM1602891396316TMj8LJQz96\n" +
                "TM1604837893365TMkL1wFyvd\n" +
                "TM1610447757838TMkrSRqlqR\n" +
                "TM1604563184676TMomQyFPvW\n" +
                "TM1605579594570TMHXJb9ARY\n" +
                "TM1610765842026TMDDUbCLOg\n" +
                "TM1612345467593TMGiyU8zl3\n" +
                "TM1609237355139TMYC2n0Uxw\n" +
                "TM1595593966684TMRqalqgHn\n" +
                "TM1610689583419TM8mC7SxIg\n" +
                "TM1610012837874TMhCo7p51W\n" +
                "TM1605242345624TMyz2ejAnm\n" +
                "TM1604563184676TMomQyFPvW\n" +
                "TM1610771880011TMdn4Tf6xh\n" +
                "TM1596099931280TMT4FRpSQN\n" +
                "TM1604277771354TMSEDOSypC\n" +
                "TM1604890796409TMewpptuTq\n" +
                "TM1610774801415TMkKmue5H1\n" +
                "TM1605006066548TMQN9V2GbM\n" +
                "TM1610696623737TMl0W58EQf\n" +
                "TM1608629502763TMbErNRkk5\n" +
                "TM1610765341706TMRhzditVS\n" +
                "TM1603512438178TMuVEUENaT\n" +
                "TM1610757537662TME6wZmZkd\n" +
                "TM1607293967251TMulRqKAsb\n" +
                "TM1610751777199TMgI8vfhM0\n" +
                "TM1609486095087TMT4xYVCnj\n" +
                "TM1605157449411TMQjnq1Bel\n" +
                "TM1610926983969TMQMuxGpqK\n" +
                "TM1595593966684TMRqalqgHn\n" +
                "TM1601456015111TMkF1X8HyF\n" +
                "TM1610710581212TMZNv4GCjE\n" +
                "TM1609235627987TM2XoTskwo\n" +
                "TM1610430573808TMwJmKhsIa\n" +
                "TM1601456015111TMkF1X8HyF\n" +
                "TM1602315194632TMj8LJQz96\n" +
                "TM1610754211405TMHEcQuH5e\n" +
                "TM1610760396586TMCtNgKS29\n" +
                "TM1603349146215TMuRCNtEM8\n" +
                "TM1610764171016TMYsqUNAMz\n" +
                "TM1611801224340TMH46Q76Gv\n" +
                "TM1604967589237TMTDxhyRld\n" +
                "TM1595647872377TMRqalqgHn\n" +
                "TM1612345467593TMGiyU8zl3\n" +
                "TM1605003191447TMAAvExk0V";

        String[] split = s.split("\n");
        StringBuilder ss = new StringBuilder();
        for (int i = 0;i<split.length;i++){
            //ss.append("\'").append(split[i]).append("\',\n");
            for (int j = 0;j<split.length;j++) {
            if (split[i].equals(split[j])){
                System.out.println(split[i]);
            }
            }
        }
        //System.out.println(ss);
    }
}
