@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryRepo;
    private final StoreRepository storeRepo;
    private final ProductRepository productRepo;

    public InventoryLevelServiceImpl(
            InventoryLevelRepository inventoryRepo,
            StoreRepository storeRepo,
            ProductRepository productRepo) {
        this.inventoryRepo = inventoryRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inv) {

        if (inv.getQuantity() < 0) {
            throw new BadRequestException("Quantity must be >= 0");
        }

        Store store = storeRepo.findById(inv.getStore().getId()).orElseThrow();
        Product product = productRepo.findById(inv.getProduct().getId()).orElseThrow();

        return inventoryRepo.findByStoreAndProduct(store, product)
                .map(existing -> {
                    existing.setQuantity(inv.getQuantity());
                    return inventoryRepo.save(existing);
                })
                .orElseGet(() -> {
                    inv.setStore(store);     // 🔥 FIX
                    inv.setProduct(product); // 🔥 FIX
                    return inventoryRepo.save(inv);
                });
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryRepo.findByStore_Id(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryRepo.findByProduct_Id(productId);
    }
}
