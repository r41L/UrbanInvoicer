package Classes;

public class clsInvoicePosition extends clsDatabaseObject
        {
        int id, invoiceId, typeId, artikelId;
        String bemerkung;
        double brutto, netto, mwst, rabat;

public clsType type;
public clsArticel articel;
public clsInvoice invoice;

public clsInvoicePosition()
        {	}

public clsInvoicePosition(int pId, int pInvoiceId, int pArtikelId, String pBemerkung,double pBrutto, double pNetto, double pMwst, double pRabat, clsType pType)
        {
        this.id=pId;
        this.invoiceId = pInvoiceId;
        this.type = pType;
        this.typeId = this.type.id;
        this.artikelId = pArtikelId;
        this.bemerkung = pBemerkung;
        this.brutto = pBrutto;
        this.netto = pNetto;
        this.mwst = pMwst;
        this.rabat = pRabat;
        }

        public void save (){}
        public void load(){}
        }