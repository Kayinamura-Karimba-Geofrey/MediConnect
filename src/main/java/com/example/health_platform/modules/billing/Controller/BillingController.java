
    private final BillingService billingService;
    private final UserService userService;

    public BillingController(BillingService billingService, UserService userService) {
        this.billingService = billingService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillDTO> createBill(@RequestParam Long patientId, @RequestParam Double amount, @RequestParam String description) {
        User patient = userService.getUserById(patientId);
        return ResponseEntity.ok(billingService.createBill(patient, amount, description));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BillDTO>> getBillsByPatient(@PathVariable Long patientId) {
        User patient = userService.getUserById(patientId);
        return ResponseEntity.ok(billingService.getBillsByPatient(patient));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    @PatchMapping("/{billId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillDTO> updateBillStatus(@PathVariable Long billId, @RequestParam BillStatus status) {
        return ResponseEntity.ok(billingService.updateBillStatus(billId, status));
    }
}
