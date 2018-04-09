package Classes;

public class clsInvoicePosition extends clsDatabaseObject {
        public int Id;
        public int InvoiceId;
        public int TypeId;
        public int ArtikelId;
        public String Bemerkung;
        public double Brutto;
        public double Netto;
        public double MwSt;
        public double Rabat;

        public clsType Type;
        public clsArticel Articel;
        public clsInvoice Invoice;

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

        public void save() {
        }

        public void load() {
        }
}