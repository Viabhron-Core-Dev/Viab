import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { StatusBar } from './components/StatusBar';
import { Sidebar } from './components/Sidebar';
import { INITIAL_APPS, NexusElement } from './types';
import { Search, Plus, Settings as SettingsIcon, Package, RotateCw, Monitor } from 'lucide-react';

export default function App() {
  const [isEditing, setIsEditing] = useState(false);
  const [apps, setApps] = useState<NexusElement[]>(INITIAL_APPS);
  const [isRecording, setIsRecording] = useState(false);
  const [lowResourceMode, setLowResourceMode] = useState(true);

  // Mock call recording trigger
  const toggleRecording = () => {
    setIsRecording(!isRecording);
  };

  return (
    <div className={`relative h-screen w-screen overflow-hidden ${lowResourceMode ? 'bg-[#111]' : 'bg-gradient-to-br from-gray-900 via-black to-slate-900'}`}>
      <StatusBar />
      
      {/* Background Dim for Edit Mode */}
      <AnimatePresence>
        {isEditing && (
          <motion.div 
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            className="fixed inset-0 bg-black/40 backdrop-blur-[2px] z-10 pointer-events-none"
          />
        )}
      </AnimatePresence>

      <main className="h-full w-full pt-12 pb-24 px-8 flex flex-col justify-between">
        {/* Top Section: Widget Shell */}
        <div className="w-full max-w-md mx-auto aspect-video glass rounded-[32px] p-8 flex flex-col items-center justify-center group relative cursor-default">
          <div className="text-5xl font-mono tracking-tighter mb-2 opacity-90">
            {new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
          </div>
          <div className="text-xs uppercase tracking-[0.2em] opacity-40 font-bold">
            Cloud Dev Intelligence
          </div>
          
          {isRecording && (
            <motion.div 
              animate={{ opacity: [1, 0.5, 1] }}
              transition={{ repeat: Infinity, duration: 1 }}
              className="absolute top-4 right-6 flex items-center gap-1.5 text-[10px] text-red-500 font-bold"
            >
              <div className="w-2 h-2 bg-red-500 rounded-full" />
              REC
            </motion.div>
          )}
        </div>

        {/* Middle: Desktop App Grid */}
        <div className="grid grid-cols-4 gap-y-10 w-full max-w-sm mx-auto flex-1 content-center">
          {apps.map((app) => (
            <motion.div
              key={app.id}
              layout
              className={`flex flex-col items-center gap-3 relative z-20 group ${isEditing ? 'edit-mode-active' : 'transition-transform active:scale-95 cursor-pointer'}`}
              onContextMenu={(e) => {
                e.preventDefault();
                setIsEditing(true);
              }}
            >
              <div 
                className={`w-14 h-14 rounded-[22px] flex items-center justify-center shadow-xl border border-white/5 transition-all ${isEditing ? 'element-lift' : ''}`}
                style={{ backgroundColor: app.color }}
              >
                {app.icon && <app.icon size={28} className="text-white drop-shadow-md" />}
                
                {isEditing && (
                  <div className="absolute -top-3 -right-3 flex flex-col gap-1 z-[60]">
                    <button className="bg-red-500 p-1.5 rounded-full shadow-lg" onClick={() => setIsEditing(false)}>
                      <Plus size={10} className="rotate-45" />
                    </button>
                    <button className="bg-blue-600 p-1.5 rounded-full shadow-lg" onClick={() => alert('App Settings')}>
                      <SettingsIcon size={10} />
                    </button>
                  </div>
                )}
              </div>
              <span className="text-[11px] font-medium tracking-tight opacity-70">
                {app.name}
              </span>
            </motion.div>
          ))}
        </div>

        {/* Bottom: Dock */}
        <div className="h-20 w-full max-w-xs mx-auto glass rounded-full px-6 flex items-center justify-around mb-4 relative z-50">
          <div className="p-3 bg-white/5 rounded-full hover:bg-white/10 transition-colors">
            <Search size={22} className="opacity-60" />
          </div>
          <div className="p-3 bg-white/5 rounded-full hover:bg-white/10 transition-colors" onClick={() => setIsEditing(!isEditing)}>
            <Plus size={22} className={isEditing ? 'text-blue-400 rotate-45 transition-transform' : ''} />
          </div>
          <div className="p-3 bg-white/5 rounded-full hover:bg-white/10 transition-colors">
            <Package size={22} className="opacity-60" />
          </div>
        </div>
      </main>

      {/* Floating System Toggles (Prototype) */}
      <div className="fixed bottom-4 left-4 z-[100] flex flex-col gap-2">
        <button 
          onClick={() => setLowResourceMode(!lowResourceMode)}
          className={`p-2 rounded-lg text-[8px] font-bold uppercase transition-all ${lowResourceMode ? 'bg-orange-500' : 'bg-white/10 opacity-30'}`}
        >
          {lowResourceMode ? 'Low RAM Mode ON' : 'High Quality'}
        </button>
        <button 
          onClick={toggleRecording}
          className={`p-2 rounded-lg text-[8px] font-bold uppercase transition-all ${isRecording ? 'bg-red-600' : 'bg-white/10 opacity-30'}`}
        >
          {isRecording ? 'Stop Rec' : 'Auto Rec'}
        </button>
      </div>

      <Sidebar isEditing={isEditing} onEditToggle={() => setIsEditing(!isEditing)} />
    </div>
  );
}
