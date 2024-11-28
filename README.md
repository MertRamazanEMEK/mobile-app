<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>README - Mobile App</title>
</head>
<body>
    <h1>Mobile App</h1>
    <p>
        Bu proje, Java ile geliştirilmiş bir mobil uygulamadır. Kullanıcılar hesap doğrulaması, ürün arama ve favorilere ekleme gibi işlemleri gerçekleştirebilir.
    </p>

    <h2>Özellikler</h2>
    <ul>
        <li><strong>Firebase Authentication:</strong> Hesap doğrulaması ve şifre sıfırlama.</li>
        <li><strong>API Kullanımı:</strong> Market sayfasındaki ürünlere erişim.</li>
        <li><strong>Favoriler:</strong> Kullanıcıların favorilere eklediği ürünleri Firebase Realtime Database'de saklama.</li>
        <li><strong>Tema ve Dil Ayarları:</strong> Uygulama temasını ve dilini özelleştirme.</li>
    </ul>

    <h2>Gereksinimler</h2>
    <ul>
        <li>Minimum SDK: 31 (Android 12)</li>
        <li>Hedef SDK: 35 (Android 14)</li>
        <li>Geliştirme Ortamı: Android Studio</li>
    </ul>

    <h2>Kurulum</h2>
    <ol>
        <li>Depoyu klonlayın:
            <pre><code>git clone https://github.com/MertRamazanEMEK/mobile-app.git</code></pre>
            veya doğrudan GitHub sayfasından zip dosyasını indirip çıkarın.
        </li>
        <li>Android Studio kullanarak projeyi açın.</li>
    </ol>

    <h2>Kullanım</h2>
    <ol>
        <li><strong>Sanal Cihazda Çalıştırma:</strong> Android Studio'daki bir emülatör üzerinden uygulamayı başlatabilirsiniz.</li>
        <li><strong>Gerçek Cihazda Çalıştırma:</strong>
            <ul>
                <li>Geliştirici seçeneklerini etkinleştirin.</li>
                <li>Telefonunuzu bilgisayara bağlayarak uygulamayı cihaz üzerinde çalıştırın.</li>
            </ul>
        </li>
    </ol>

    <h3>Uygulamada Bulunan Sayfalar</h3>
    <ul>
        <li>Hesap Yönetimi: Giriş yapma, hesap oluşturma, şifre sıfırlama.</li>
        <li>Ürün Arama: API üzerinden ürün arama ve listeleme.</li>
        <li>Favori Ürünler: Ürünleri favorilere ekleme ve yönetme.</li>
        <li>Tema ve Dil Ayarları: Uygulama temasını ve dilini değiştirme.</li>
    </ul>

    <h2>Lisans</h2>
    <p>
        Bu proje açık kaynaklı değildir ve dış katkılara kapalıdır.
    </p>
</body>
</html>
