package Classes;

public class clsInvoicePosition extends clsDatabaseObject {
        private int Id;
        private int InvoiceId;
        public String Bemerkung;
        public double Brutto;
        public double Netto;
        public double MwSt;
        public double Rabat;
        public int ArtikelId;
        public int TypeId;

        private clsType Type;
        private clsArticel Articel;
        private clsInvoice Invoice;

        public clsInvoicePosition() {
        }

        public clsInvoicePosition(int pId, int pInvoiceId, int pArtikelId, String pBemerkung, double pBrutto, double pNetto, double pMwst, double pRabat, clsType pType) {
                this.Id = pId;
                this.InvoiceId = pInvoiceId;
                this.Type = pType;
                this.TypeId = this.Type.id;
                this.ArtikelId = pArtikelId;
                this.Bemerkung = pBemerkung;
                this.Brutto = pBrutto;
                this.Netto = pNetto;
                this.MwSt = pMwst;
                this.Rabat = pRabat;
        }

        public void SetInvoiceId(int pInvoiceId)
        {
                this.InvoiceId = pInvoiceId;
        }

        public void save() {
                String tmpCommand = "INSERT INTO tbInvoicePosition (InvoiceId, Bemerkung, Brutto, Netto, MwSt, Rabat, ArtikelId, TypeId) VALUES ("+InvoiceId+","+Bemerkung+","+Brutto+","+Netto+","+MwSt+","+Rabat+","+ArtikelId+","+TypeId+")";
        }

        public void load() {
        }
}