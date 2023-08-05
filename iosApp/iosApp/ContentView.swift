import SwiftUI
import shared

struct ContentView: View {
	let currencyHelper = CurrencyHelper()

	var body: some View {
        ZStack{
            ComposeView(currencyHelper: currencyHelper)
        }
	}
}

struct ComposeView: UIViewControllerRepresentable{
    let currencyHelper: CurrencyHelper
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
    
    func makeUIViewController(context: Context) -> some UIViewController {
        return  Main_iosKt.MainViewController(currencyHelper: currencyHelper)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
